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

import java.util.ArrayList;

public class ShowFoodInfo extends AppCompatActivity {

    TextView foodTitle;
    TextView foodMat;
    TextView foodInfo;
    FoodData2 foodData2;
    ArrayList<String> ingredientDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_food_info);

        Intent intent = getIntent();
        int taiyaki = intent.getIntExtra("foodData", -1);
        foodData2 = new FoodData2(taiyaki, "ja", getApplicationContext());

        foodTitle = findViewById(R.id.food_title);
        foodMat = findViewById(R.id.food_mat);
        foodInfo = findViewById(R.id.food_info);

        foodTitle.setText(foodData2.getFoodName());

        ArrayList<String> ingredientDetail = foodData2.getIngredientDetail();
        StringBuilder ss = new StringBuilder();
        for (String s : ingredientDetail) {
            ss.append("・" + s + "\n");
        }
        foodMat.setText(ss.toString());
        foodInfo.setText(foodData2.getDetail());

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
