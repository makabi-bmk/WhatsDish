package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Religion extends AppCompatActivity {

    private String fileName = "religion.json";
    private String jsonReligiousData = null;
    static List<ReligionData> religionDataList = new ArrayList<>();
    Gson gson = new Gson();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.religion);

        //宗教情報をセット
        openReligionDataFile();

        final ReligionButton budd = findViewById(R.id.buddhism);
        budd.setValue("buddhism", religionDataList.get(0).isSelected);
        budd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("weiwei", "43;" + budd.isSelect());
                religionDataList.set(0, new ReligionData("buddhism", budd.isSelect()));
            }
        });

//        final ImageButton buddhism = findViewById(R.id.buddhism);
//        buddhism.setOnClickListener(new View.OnClickListener() {
//            boolean isSelected = false;
//            @Override
//            public void onClick(View view) {
//                isSelected = !isSelected;
//                //buddhism.setImageResource(changeReligionPicture("buddhism" , isSelected));
//                //changeReligionPicture("buddhism", isSelected);
////                if (isSelected == false){
////                    //食品リストをハッシュマップに追加
////                    isSelected = 1;
////                    buddhism.setImageResource(R.drawable.buddhism_check);
////                } else {
////                    //食品リストを削除
////                    isSelected = 0;
////
////                    religionDataList.set(0, new ReligionData("buddhism", false));
////                    buddhism.setImageResource(R.drawable.buddhism);
////                }
//                Log.d("weiwei", "buddhism:" + isSelected);
//            }
//        });

        final ImageButton christ = findViewById(R.id.christ);
        christ.setOnClickListener(new View.OnClickListener() {
            int christ_flag = 0;
            @Override
            public void onClick(View view) {
                if (christ_flag == 0){
                    //食品リストをハッシュマップに追加
                    christ_flag = 1;
                    christ.setImageResource(R.drawable.christ_check);
                } else {
                    //食品リストを削除
                    christ_flag = 0;
                    christ.setImageResource(R.drawable.christ);
                }

            }
        });

        final ImageButton hinduism = findViewById(R.id.hinduism);
        hinduism.setOnClickListener(new View.OnClickListener() {
            int hinduism_flag = 0;
            @Override
            public void onClick(View view) {
                if (hinduism_flag == 0){
                    //食品リストをハッシュマップに追加
                    hinduism_flag = 1;
                    hinduism.setImageResource(R.drawable.hinduism_check);
                } else {
                    //食品リストを削除
                    hinduism_flag = 0;
                    hinduism.setImageResource(R.drawable.hinduism);
                }
            }
        });

        final ImageButton islam = findViewById(R.id.islam);
        islam.setOnClickListener(new View.OnClickListener() {
            int islam_flag = 0;
            @Override
            public void onClick(View view) {
                if (islam_flag == 0){
                    //食品リストを追加
                    islam_flag = 1;
                    islam.setImageResource(R.drawable.islam_check);
                } else {
                    //食品リストを削除
                    islam_flag = 0;
                    islam.setImageResource(R.drawable.islam);
                }
            }
        });

        final ImageButton judaism = findViewById(R.id.judaism);
        judaism.setOnClickListener(new View.OnClickListener() {
            int judaism_flag = 0;
            @Override
            public void onClick(View view) {
                if (judaism_flag == 0){
                    //食品リストを追加
                    judaism_flag = 1;
                    judaism.setImageResource(R.drawable.judaism_check);
                } else {
                    //食品リストを削除
                    judaism_flag = 0;
                    judaism.setImageResource(R.drawable.judaism);
                }
            }
        });
        final ImageButton shinto = findViewById(R.id.shinto);
        shinto.setOnClickListener(new View.OnClickListener() {
            int shinto_flag = 0;
            @Override
            public void onClick(View view) {
                if (shinto_flag == 0) {
                    //食品リストを追加
                    shinto_flag = 1;
                    shinto.setImageResource(R.drawable.shinto_check);
                } else {
                    //食品リストを削除
                    shinto_flag = 0;
                    shinto.setImageResource(R.drawable.shinto);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("weiwei", "onDestroy:budd = " + religionDataList.get(0).isSelected);
        saveData();
    }



    // ファイルを読み出し
    public void openReligionDataFile() {

        // try-with-resources
        try (FileInputStream fileInputStream = openFileInput(fileName);
             BufferedReader reader= new BufferedReader(
                     new InputStreamReader(fileInputStream, "UTF-8"))) {

            String lineBuffer;
            while( (lineBuffer = reader.readLine()) != null ) {
                jsonReligiousData = lineBuffer;
            }
            religionDataList = gson.fromJson(jsonReligiousData, new TypeToken<List<ReligionData>>(){}.getType());
        } catch (FileNotFoundException e) {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        // try-with-resources
        try (FileOutputStream fileOutputstream = openFileOutput(fileName,
                Context.MODE_PRIVATE)){

            jsonReligiousData = gson.toJson(religionDataList);
            fileOutputstream.write(jsonReligiousData.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initData() {
        if (fileName.equals("religion.json")) {

            //宗教情報をセット
            Gson gson = new Gson();
            ReligionData[] religionData = new ReligionData[6];

            religionData[0] = new ReligionData("buddhism", false);
            religionData[1] = new ReligionData("christ", false);
            religionData[2] = new ReligionData("hinduism", false);
            religionData[3] = new ReligionData("islam", false);
            religionData[4] = new ReligionData("judaism", false);
            religionData[5] = new ReligionData("shinto", false);
            Collections.addAll(religionDataList, religionData);
            jsonReligiousData = gson.toJson(religionDataList);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return false;
    }
}
