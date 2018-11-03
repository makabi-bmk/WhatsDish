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

public class Allergies  extends AppCompatActivity {

    private String fileName = "allergies.json";
    private String jsonAllergiesData = null;
    private final int allergiesNum = 25;
    static List<Data> DataList = new ArrayList<>();
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allergies);

        openAllergiesDataFile();

//        for (Data l : DataList) {
//            if (l.isSelect) {
//                l.allergiesName;
//            }
//        }

        AllergiesButton[] allergiesButtons = new AllergiesButton[allergiesNum];
        for (int i = 0; i < allergiesNum; i++) {
            Resources res = getResources();
            int stringID = res.getIdentifier("allergies_" + i, "string", getPackageName());
            String allergiesName = res.getString(stringID);
            int resourseID = res.getIdentifier(allergiesName, "id", getPackageName());

            allergiesButtons[i] = findViewById(resourseID);
            allergiesButtons[i].setValue(i, allergiesName, DataList.get(i).isSelect);
            allergiesButtons[i].setOnClickListener(new View.OnClickListener() { public void onClick(View v) {}});
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("weiwei", "onDestroy");
        for (Data l : DataList) {
            Log.d("weiwei", l.name + ":" + l.isSelect);
        }
        saveData();
    }

    public void openAllergiesDataFile() {

        // try-with-resources
        try (FileInputStream fileInputStream = openFileInput(fileName);
             BufferedReader reader= new BufferedReader(
                     new InputStreamReader(fileInputStream, "UTF-8"))) {

            String lineBuffer;
            while( (lineBuffer = reader.readLine()) != null ) {
                jsonAllergiesData = lineBuffer;
            }
            DataList = gson.fromJson(jsonAllergiesData, new TypeToken<List<Data>>(){}.getType());
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

            jsonAllergiesData = gson.toJson(DataList);
            fileOutputstream.write(jsonAllergiesData.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initData() {
        if (fileName.equals("allergies.json")) {

            Gson gson = new Gson();
            Data[] Datas = new Data[allergiesNum];

            Datas[0] = new Data("shrimp", false);
            Datas[1] = new Data("crab", false);
            Datas[2] = new Data("buckwheat", false);
            Datas[3] = new Data("wheat", false);
            Datas[4] = new Data("egg", false);
            Datas[5] = new Data("milk", false);
            Datas[6] = new Data("peanuts", false);
            Datas[7] = new Data("squid", false);
            Datas[8] = new Data("salmon_roe", false);
            Datas[9] = new Data("orange", false);
            Datas[10] = new Data("cashewnuts", false);
            Datas[11] = new Data("kiwi", false);
            Datas[12] = new Data("cow", false);
            Datas[13] = new Data("walnut", false);
            Datas[14] = new Data("sesame", false);
            Datas[15] = new Data("fish", false);
            Datas[16] = new Data("soy", false);
            Datas[17] = new Data("chicken", false);
            Datas[18] = new Data("banana", false);
            Datas[19] = new Data("pig", false);
            Datas[20] = new Data("mushroom", false);
            Datas[21] = new Data("peach", false);
            Datas[22] = new Data("yam", false);
            Datas[23] = new Data("apple", false);
            Datas[24] = new Data("gelatin", false);

            Collections.addAll(DataList, Datas);
            jsonAllergiesData = gson.toJson(DataList);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return false;
    }
}
