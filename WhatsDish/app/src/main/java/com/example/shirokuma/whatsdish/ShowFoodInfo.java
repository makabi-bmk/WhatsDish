package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowFoodInfo extends AppCompatActivity {

    TextView foodTitle;
    LinearLayout foodMat;
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
        dishData = new DishData(taiyaki, getApplicationContext());

        foodTitle = findViewById(R.id.food_title);
        foodMat = findViewById(R.id.food_mat);
        foodInfo = findViewById(R.id.food_info);
        foodPicture = findViewById(R.id.food_picture);

        foodTitle.setText(dishData.getName());
        dishData.setBitmap();
        foodPicture.setImageBitmap(dishData.getPicture());

        ArrayList<String> foodMat = dishData.getMat();
        for (String s : foodMat) {
            TextView textView = new TextView(this);
            textView.setText("・" + s);
            this.foodMat.addView(textView);
        }

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
