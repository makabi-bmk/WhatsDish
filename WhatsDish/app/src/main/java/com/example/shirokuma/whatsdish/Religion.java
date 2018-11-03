package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

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
    private String jsonReligionData = null;
    private final int religionNum = 6;
    static List<Data> religionList = new ArrayList<>();
    Gson gson = new Gson();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.religion);

        //宗教情報をセット
        openReligionDataFile();

        //ImageButtonの配置
        ReligionButton[] religionButtons = new ReligionButton[religionNum];
        for (int i = 0; i < religionNum; i++) {
            Resources res = getResources();
            int stringID = res.getIdentifier("religion_" + i, "string", getPackageName());
            String religionName = res.getString(stringID);
            int resourseID = res.getIdentifier(religionName, "id", getPackageName());

            religionButtons[i] = findViewById(resourseID);
            religionButtons[i].setValue(i, religionName, religionList.get(i).isSelect);
            religionButtons[i].setOnClickListener(new View.OnClickListener() { public void onClick(View v) {}});
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("weiwei", "onDestroy");
        
        for (Data l : religionList) {
            Log.d("weiwei", l.name + ":" + l.isSelect);
        }
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
                jsonReligionData = lineBuffer;
            }
            religionList = gson.fromJson(jsonReligionData, new TypeToken<List<Data>>(){}.getType());
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
            jsonReligionData = gson.toJson(religionList);
            fileOutputstream.write(jsonReligionData.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initData() {
        if (fileName.equals("religion.json")) {

            //宗教情報をセット
            Gson gson = new Gson();
            Data[] Datas = new Data[6];

            Datas[0] = new Data("buddhism", false);
            Datas[1] = new Data("christ", false);
            Datas[2] = new Data("hinduism", false);
            Datas[3] = new Data("islam", false);
            Datas[4] = new Data("judaism", false);
            Datas[5] = new Data("shinto", false);
            Collections.addAll(religionList, Datas);
            jsonReligionData = gson.toJson(religionList);
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
