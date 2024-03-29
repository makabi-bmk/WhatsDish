package com.example.shirokuma.whatsdish;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequest;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.api.services.vision.v1.model.ImageContext;

import org.apache.lucene.search.spell.LevensteinDistance;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // API呼び出し時のヘッダ指定
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    // GALLARYへのアクセスのための定数
    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;

    String selectLang = "ja";

    //食材ファイルを開くための変数
    public static File ingredientFile = new File();
    public static File allergyFile = new File();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //カメラを選択するボタン
        FloatingActionButton selectImg = findViewById(R.id.camera_fab);
        selectImg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            @Override
            public void onClick(View view) {
                startCamera();
            }
        });

        //食材リストファイルを開く
        ingredientFile.setFile("ingredient.json", getApplicationContext());
        allergyFile.setFile("allergies.json", getApplicationContext());
    }

    //カメラが選択されたときの処理
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public void startCamera() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
    }

    // 画像ファイルの選択ができたときの処理
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // もしカメラ画像が取得できたらアップロード
        if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Bitmap bitmap;
            // cancelしたケースも含む
            if (data.getExtras() == null) {
                Log.d("debug", "cancel ?");
            } else {
                bitmap = (Bitmap) data.getExtras().get("data");
                if (bitmap != null) {
                    uploadImage(bitmap);
                }
            }
        }
    }

    // 画像アクセスのための権限設定
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_IMAGE_REQUEST) {
            if (PermissionUtils.permissionGranted(requestCode, CAMERA_PERMISSIONS_REQUEST, grantResults)) {
                startCamera();
            }
        }
    }

    // 画像のOCR処理
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public void uploadImage(Bitmap ocrBitmap) {
        try {
            // Google Cloud Vision APIの呼び出し
            callCloudVision(ocrBitmap);

        } catch (IOException e) {
            Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
        }
    }

    //GoogleAPIの呼び出し
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @SuppressLint("StaticFieldLeak")
    private void callCloudVision(final Bitmap bitmap) throws IOException {

        //ローディング画面のダイアログを表示させる
        final DialogFragment loadingDialog = new LoadingDialogFragment();
        loadingDialog.setCancelable(false);
        loadingDialog.show(getSupportFragmentManager(), "");

        // API呼び出しを行うための非同期処理
        new AsyncTask<Object, Void, String>() {
            @Override
            protected String doInBackground(Object... params) {
                try {
                    HttpTransport http = AndroidHttp.newCompatibleTransport();
                    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

                    // Vision API呼び出しのための初期処理
                    VisionRequestInitializer reqInitializer =
                            new VisionRequestInitializer(getString(R.string.vision_api_key)) {

                                @Override
                                protected void initializeVisionRequest(VisionRequest<?> visionRequest)
                                        throws IOException {
                                    super.initializeVisionRequest(visionRequest);

                                    String packageName = getPackageName();
                                    visionRequest.getRequestHeaders().set(ANDROID_PACKAGE_HEADER, packageName);

                                    String sig = PackageManagerUtils.getSignature(getPackageManager(), packageName);
                                    visionRequest.getRequestHeaders().set(ANDROID_CERT_HEADER, sig);
                                }
                            };

                    // リクエストの作成
                    Vision.Builder builder = new Vision.Builder(http, jsonFactory, null);
                    builder.setVisionRequestInitializer(reqInitializer);

                    Vision vision = builder.build();

                    BatchAnnotateImagesRequest batchImgReq =
                            new BatchAnnotateImagesRequest();

                    batchImgReq.setRequests(new ArrayList<AnnotateImageRequest>() {{
                        AnnotateImageRequest annotateImgReq = new AnnotateImageRequest();

                        // 画像のJPEGへの変換
                        Image base64Image = new Image();
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        byte[] imageBytes = out.toByteArray();

                        base64Image.encodeContent(imageBytes);
                        annotateImgReq.setImage(base64Image);

                        // Vision APIのFeatures設定
                        annotateImgReq.setFeatures(new ArrayList<Feature>() {{
                            Feature textDetect = new Feature();

                            // OCR 文字認識’TEXT_DETECTION’を使う
                            textDetect.setType("TEXT_DETECTION");
                            textDetect.setMaxResults(10);
                            add(textDetect);

                        }});


                        // UIで選択された言語を取得する
                        List<String> langHint = new ArrayList<>();
                        langHint.add(selectLang);

                        ImageContext ic = new ImageContext();
                        ic.setLanguageHints(langHint);

                        annotateImgReq.setImageContext(ic);


                        // リクエストにセット
                        add(annotateImgReq);
                    }});

                    // Vison APIの呼び出し
                    Vision.Images.Annotate annotateRequest =
                            vision.images().annotate(batchImgReq);

                    BatchAnnotateImagesResponse response = annotateRequest.execute();
                    return convertResponseToString(response);

                } catch (IOException e) {
                    // エラー処理

                }
                return getString(R.string.call_api_error);
            }

            // 解析結果を表示
            protected void onPostExecute(String result) {

                //Loadingのダイアログを閉じる
                loadingDialog.dismiss();

                String[] receivedData = result.split("\n", 0);
                //食事データの呼び出し
                ArrayList<String> foodName = new ArrayList<>();
                int i = 0;
                while (true) {
                    int strID;
                    strID = getResources().getIdentifier("food_name_var_" + i, "string", getPackageName());
                    if (strID == 0) {
                        break;
                    }
                    foodName.add(getResources().getString(strID));
                    i++;
                }

                LevensteinDistance distance = new LevensteinDistance();
                ArrayList<String> storeFoodname = new ArrayList<>();
                ArrayList<Integer> foodID = new ArrayList<>();
                boolean existsSimilarString = false;

                int foodNameLength = foodName.size();
                for(int j = 0; j < foodNameLength; j++) {
                    for(String receivedName : receivedData) {
                        String name = foodName.get(j);
                        if (storeFoodname.indexOf(name) == -1) {
                            if(distance.getDistance(name, receivedName) > 0.3) {
                                foodID.add(j);
                                storeFoodname.add(name);
                                existsSimilarString = true;
                            }
                        }
                    }
                }
                //一致する食事データが見つからなかった場合
                if (!existsSimilarString) {
                    //失敗のダイアログを表示
                    DialogFragment missingDialog = new ButtonDialogFragment();
                    missingDialog.setCancelable(false);
                    Bundle args = new Bundle();
                    args.putString("message", getResources().getString(R.string.missing_parse));
                    missingDialog.setArguments(args);
                    missingDialog.show(getSupportFragmentManager(), "dialog");
                } else {
                    //成功のダイアログを表示
                    DialogFragment completeDialog = new ButtonDialogFragment();
                    completeDialog.setCancelable(false);
                    Bundle args = new Bundle();
                    args.putString("message", getResources().getString(R.string.compete_parse));
                    args.putIntegerArrayList("foodID", foodID);
                    completeDialog.setArguments(args);
                    completeDialog.show(getSupportFragmentManager(), "dialog");
                }
            }
        }.execute();
    }

    // レスポンスからの文字列検出
    private String convertResponseToString(BatchAnnotateImagesResponse response) {
        String message = "";

        List<EntityAnnotation> ocrData = response.getResponses().get(0).getTextAnnotations();
        if (ocrData != null) {
            message += ocrData.get(0).getDescription();
        } else {
            message += R.string.text_detection_error;
        }

        return message;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_allergy) {
            Intent intent = new Intent(MainActivity.this, Allergies.class);
            startActivity(intent);
        } else if (id == R.id.nav_cannotEat) {
            Intent intent = new Intent(MainActivity.this, ShowIngredientList1.class);
            startActivity(intent);
        } else if (id == R.id.nav_religion) {
            Intent intent = new Intent(MainActivity.this, Religion.class);
            startActivity(intent);
        } else if (id == R.id.nav_jpn_ingredient_list) {
            Intent intent = new Intent(MainActivity.this, ShowJpnIngredientDetail1.class);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
