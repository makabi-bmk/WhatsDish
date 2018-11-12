package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowFoodInfo extends AppCompatActivity {

    TextView foodTitle;
    TextView foodMat;
    TextView foodInfo;
    DishData dishData;
    ImageView foodPicture;
    ArrayList<String> ingredientDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_food_info);

        Intent intent = getIntent();
        int taiyaki = intent.getIntExtra("foodData", -1);
        dishData = new DishData(taiyaki, "ja", getApplicationContext());

        foodTitle = findViewById(R.id.food_title);
        foodMat = findViewById(R.id.food_mat);
        foodInfo = findViewById(R.id.food_info);
        foodPicture = findViewById(R.id.food_picture);

        foodTitle.setText(dishData.getName());
        foodPicture.setImageBitmap(dishData.getPicture());

        ArrayList<String> ingredientDetail = dishData.getIngredientDetail();
        StringBuilder ss = new StringBuilder();
        for (String s : ingredientDetail) {
            ss.append("・" + s + "\n");
        }
        foodMat.setText(ss.toString());
        foodInfo.setText(dishData.getDetail());

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
