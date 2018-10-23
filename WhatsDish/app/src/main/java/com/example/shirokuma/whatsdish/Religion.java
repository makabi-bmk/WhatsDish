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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Religion extends AppCompatActivity {
    public HashMap<String, String> religion = new HashMap<>();

    private String fileName = "religion.json";
    private String text = null;
    List<ReligionData> list = new ArrayList<>();
    Gson gson;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.religion);

        //宗教情報をセット
        openFile();

        for (ReligionData i : list) {
            Log.d("weiweiwei", i.relifionName + " " + i.isSelected);
        }

        final ImageButton buddhism = (ImageButton)findViewById(R.id.buddhism);
        buddhism.setOnClickListener(new View.OnClickListener() {
            int buddhism_flag = 0;
            @Override
            public void onClick(View view) {
                if (buddhism_flag == 0){
                    list.set(0, new ReligionData("buddhism", true));
                    //食品リストをハッシュマップに追加
                    buddhism_flag = 1;
                    buddhism.setImageResource(R.drawable.buddhism_check);
                } else {
                    //食品リストを削除
                    buddhism_flag = 0;
                    buddhism.setImageResource(R.drawable.buddhism);
                }
            }
        });

        final ImageButton christ = (ImageButton)findViewById(R.id.christ);
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

        final ImageButton hinduism = (ImageButton)findViewById(R.id.hinduism);
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

        final ImageButton islam = (ImageButton)findViewById(R.id.islam);
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

        final ImageButton judaism = (ImageButton)findViewById(R.id.judaism);
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
        final ImageButton shinto = (ImageButton)findViewById(R.id.shinto);
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
        saveData();
    }

    // ファイルを読み出し
    public String openFile() {
        String text = null;

        // try-with-resources
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             BufferedReader reader= new BufferedReader(
                     new InputStreamReader(fileInputStream, "UTF-8"))) {

            String lineBuffer;
            while( (lineBuffer = reader.readLine()) != null ) {
                text = lineBuffer ;
            }

        } catch (FileNotFoundException e) {
            initData();
            Log.d("weiwei", "ファイルが無かったYO");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<ReligionData> setData() {
        Gson gson = new Gson();
        //Type collectionType = new TypeToken<Collection<ReligionData>>(){}.getType();
        //return gson.fromJson(text, collectionType);
        return gson.fromJson(text, new TypeToken<List<ReligionData>>(){}.getType());
    }

    public void saveData() {
        // try-with-resources
        try (FileOutputStream fileOutputstream = openFileOutput(fileName,
                Context.MODE_PRIVATE);){

            String str = gson.toJson(list);
            fileOutputstream.write(str.getBytes());

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


            for (ReligionData data : religionData) {
                list.add(data);
            }
            text = gson.toJson(list);
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
