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

    static File allergyFile = new File();
    private final int allergiesNum = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allergies);

        allergyFile.setFile("allergies.json", getApplicationContext());

        AllergiesButton[] allergiesButtons = new AllergiesButton[allergiesNum];
        for (int i = 0; i < allergiesNum; i++) {
            Resources res = getResources();
            int stringID = res.getIdentifier("allergies_" + i, "string", getPackageName());
            String allergiesName = res.getString(stringID);
            int resourseID = res.getIdentifier(allergiesName, "id", getPackageName());

            allergiesButtons[i] = findViewById(resourseID);
            Log.d("weiwei", "name = " + allergyFile.getList().get(i).name + ", isSelect = " + allergyFile.getList().get(i).isSelect);
            allergiesButtons[i].setValue(i);
            allergiesButtons[i].setOnClickListener(new View.OnClickListener() { public void onClick(View v) {}});
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("weiwei", "onDestroy");
        for (Data l : allergyFile.getList()) {
            Log.d("weiwei", l.name + ":" + l.isSelect);
        }
        allergyFile.saveData();
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
