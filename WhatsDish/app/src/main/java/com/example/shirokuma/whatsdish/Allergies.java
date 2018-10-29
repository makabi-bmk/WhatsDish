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
    static List<AllergiesDataFormat> allergiesDataFormatList = new ArrayList<>();
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allergies);

        openAllergiesDataFile();

        AllergiesButton[] allergiesButtons = new AllergiesButton[allergiesNum];
        for (int i = 0; i < allergiesNum; i++) {
            Resources res = getResources();
            int stringID = res.getIdentifier("allergies_" + i, "string", getPackageName());
            String allergiesName = res.getString(stringID);
            int resourseID = res.getIdentifier(allergiesName, "id", getPackageName());

            allergiesButtons[i] = findViewById(resourseID);
            allergiesButtons[i].setValue(i, allergiesName, allergiesDataFormatList.get(i).isSelect);
            allergiesButtons[i].setOnClickListener(new View.OnClickListener() { public void onClick(View v) {}});
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("weiwei", "onDestroy");
        for (AllergiesDataFormat l : allergiesDataFormatList) {
            Log.d("weiwei", l.allergiesName + ":" + l.isSelect);
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
            allergiesDataFormatList = gson.fromJson(jsonAllergiesData, new TypeToken<List<AllergiesDataFormat>>(){}.getType());
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

            jsonAllergiesData = gson.toJson(allergiesDataFormatList);
            fileOutputstream.write(jsonAllergiesData.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initData() {
        if (fileName.equals("allergies.json")) {

            Gson gson = new Gson();
            AllergiesDataFormat[] allergiesDataFormats = new AllergiesDataFormat[allergiesNum];

            allergiesDataFormats[0] = new AllergiesDataFormat("shrimp", false);
            allergiesDataFormats[1] = new AllergiesDataFormat("crab", false);
            allergiesDataFormats[2] = new AllergiesDataFormat("buckwheat", false);
            allergiesDataFormats[3] = new AllergiesDataFormat("wheat", false);
            allergiesDataFormats[4] = new AllergiesDataFormat("egg", false);
            allergiesDataFormats[5] = new AllergiesDataFormat("milk", false);
            allergiesDataFormats[6] = new AllergiesDataFormat("peanuts", false);
            allergiesDataFormats[7] = new AllergiesDataFormat("squid", false);
            allergiesDataFormats[8] = new AllergiesDataFormat("salmon_roe", false);
            allergiesDataFormats[9] = new AllergiesDataFormat("orange", false);
            allergiesDataFormats[10] = new AllergiesDataFormat("cashewnuts", false);
            allergiesDataFormats[11] = new AllergiesDataFormat("kiwi", false);
            allergiesDataFormats[12] = new AllergiesDataFormat("cow", false);
            allergiesDataFormats[13] = new AllergiesDataFormat("walnut", false);
            allergiesDataFormats[14] = new AllergiesDataFormat("sesame", false);
            allergiesDataFormats[15] = new AllergiesDataFormat("fish", false);
            allergiesDataFormats[16] = new AllergiesDataFormat("soy", false);
            allergiesDataFormats[17] = new AllergiesDataFormat("chicken", false);
            allergiesDataFormats[18] = new AllergiesDataFormat("banana", false);
            allergiesDataFormats[19] = new AllergiesDataFormat("pig", false);
            allergiesDataFormats[20] = new AllergiesDataFormat("mushroom", false);
            allergiesDataFormats[21] = new AllergiesDataFormat("peach", false);
            allergiesDataFormats[22] = new AllergiesDataFormat("yam", false);
            allergiesDataFormats[23] = new AllergiesDataFormat("apple", false);
            allergiesDataFormats[24] = new AllergiesDataFormat("gelatin", false);

            Collections.addAll(allergiesDataFormatList, allergiesDataFormats);
            jsonAllergiesData = gson.toJson(allergiesDataFormatList);
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
