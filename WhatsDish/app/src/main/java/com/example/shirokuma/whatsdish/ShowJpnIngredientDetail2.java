package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowJpnIngredientDetail2 extends AppCompatActivity {

    TextView ingredientName;
    TextView ingredientDetail;
    ImageView ingredientPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_jpn_ingredient_detail2);

        Intent intent = getIntent();
        int jpnIngredientID = intent.getIntExtra("jpnIngredientNum", -1);

        ingredientName = findViewById(R.id.jpn_ingredient_title);
        ingredientDetail = findViewById(R.id.jpn_ingredient_detail);
        ingredientPicture = findViewById(R.id.jpn_ingredient_picture);

        int strID;
        strID = getResources().getIdentifier("jpn_ingredient_name_" + jpnIngredientID, "string", getPackageName());
        String title = getResources().getString(strID);
        ingredientName.setText(title);
        //TODO: あとで写真設定する
//        strID = getResources().getIdentifier("")
//        ingredientPicture.setImageBitmap(dishData.getPicture());

        strID = getResources().getIdentifier("jpn_ingredient_detail_" + jpnIngredientID, "string", getPackageName());
        String detail = getResources().getString(strID);
        ingredientDetail.setText(detail);
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
