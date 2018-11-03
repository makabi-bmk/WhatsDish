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

    static File religionFile = new File();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.religion);

        religionFile.setFile("religion.json", getApplicationContext());

        //ImageButtonの配置
        ReligionButton[] religionButtons = new ReligionButton[6];
        Log.d("weiwei", "listsize = " + religionFile.getList().size());
        for (int i = 0; i < 6; i++) {
            Resources res = getResources();
            int stringID = res.getIdentifier("religion_" + i, "string", getPackageName());
            String religionName = res.getString(stringID);
            int resourseID = res.getIdentifier(religionName, "id", getPackageName());

            religionButtons[i] = findViewById(resourseID);
            religionButtons[i].setValue(i);
            religionButtons[i].setOnClickListener(new View.OnClickListener() { public void onClick(View v) {}});
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("weiwei", "onDestroy");
        
        for (Data l : religionFile.getList()) {
            Log.d("weiwei", l.name + ":" + l.isSelect);
        }
        religionFile.saveData();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return false;
    }
}
