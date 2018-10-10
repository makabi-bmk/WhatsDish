package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ShowFoodInfo extends AppCompatActivity {

    TextView foodTitle;
    TextView foodMat;
    TextView foodInfo;
    FoodData foodData = new FoodData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_food_info);

        Intent intent = getIntent();
        String foodName = intent.getStringExtra("foodName");

        foodTitle = findViewById(R.id.food_title);
        foodMat = findViewById(R.id.food_mat);
        foodInfo = findViewById(R.id.food_info);

        foodTitle.setText(foodName);
        foodMat.setText(foodData.foodMat.get(foodName));
        foodInfo.setText(foodData.foodInfo.get(foodName));

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return false;
    }
}
