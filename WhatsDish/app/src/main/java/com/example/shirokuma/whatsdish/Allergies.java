package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static com.example.shirokuma.whatsdish.MainActivity.allergyFile;
import static com.example.shirokuma.whatsdish.MainActivity.ingredientFile;

public class Allergies  extends AppCompatActivity {

    private final int allergiesNum = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allergies);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        allergyFile.setFile("allergies.json", getApplicationContext());

        AllergiesButton[] allergiesButtons = new AllergiesButton[allergiesNum];
        for (int i = 0; i < allergiesNum; i++) {
            Resources res = getResources();
            int stringID = res.getIdentifier("allergies_" + i, "string", getPackageName());
            String allergiesName = res.getString(stringID);
            int resourseID = res.getIdentifier(allergiesName, "id", getPackageName());

            allergiesButtons[i] = findViewById(resourseID);
            allergiesButtons[i].initData();
            allergiesButtons[i].setValue(i);
            allergiesButtons[i].setOnClickListener(new View.OnClickListener() { public void onClick(View v) {}});
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("weiwei", "onDestroy");
        allergyFile.saveData();
        ingredientFile.saveData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.call_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.icon) {
            Intent intent = new Intent(Allergies.this, SetLanguage1.class);
            startActivity(intent);
        }
        return true;
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
