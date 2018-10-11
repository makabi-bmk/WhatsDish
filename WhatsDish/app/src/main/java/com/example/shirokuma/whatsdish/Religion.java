package com.example.shirokuma.whatsdish;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.HashMap;

public class Religion extends AppCompatActivity {
    public HashMap<String, String> religion = new HashMap<>();

    protected void onCreate(){
        setContentView(R.layout.religion);

        final ImageButton buddhism = (ImageButton)findViewById(R.id.buddhism);
        buddhism.setOnClickListener(new View.OnClickListener() {
            int buddhism_flag = 0;
            @Override
            public void onClick(View view) {
                if (buddhism_flag == 0){
                    //食品リストをハッシュマップに追加
                    buddhism_flag = 1;
                    buddhism.setImageResource(R.drawable.buddhism_check);
                } else {
                    //食品リストを削除
                    buddhism_flag = 0;
                    buddhism.setImageResource(R.drawable.buddhism);
                }
            }
        });

        final ImageButton christ = (ImageButton)findViewById(R.id.christ);
        christ.setOnClickListener(new View.OnClickListener() {
            int christ_flag = 0;
            @Override
            public void onClick(View view) {
                if (christ_flag == 0){
                    //食品リストをハッシュマップに追加
                    christ_flag = 1;
                    christ.setImageResource(R.drawable.christ_check);
                } else {
                    //食品リストを削除
                    christ_flag = 0;
                    christ.setImageResource(R.drawable.christ);
                }

            }
        });

        final ImageButton hinduism = (ImageButton)findViewById(R.id.hinduism);
        hinduism.setOnClickListener(new View.OnClickListener() {
            int hinduism_flag = 0;
            @Override
            public void onClick(View view) {
                if (hinduism_flag == 0){
                    //食品リストをハッシュマップに追加
                    hinduism_flag = 1;
                    hinduism.setImageResource(R.drawable.hinduism_check);
                } else {
                    //食品リストを削除
                    hinduism_flag = 0;
                    hinduism.setImageResource(R.drawable.hinduism);
                }
            }
        });

        final ImageButton islam = (ImageButton)findViewById(R.id.islam);
        islam.setOnClickListener(new View.OnClickListener() {
            int islam_flag = 0;
            @Override
            public void onClick(View view) {
                if (islam_flag == 0){
                    //食品リストを追加
                    islam_flag = 1;
                    islam.setImageResource(R.drawable.islam_check);
                } else {
                    //食品リストを削除
                    islam_flag = 0;
                    islam.setImageResource(R.drawable.islam);
                }
            }
        });

        final ImageButton judaism = (ImageButton)findViewById(R.id.judaism);
        judaism.setOnClickListener(new View.OnClickListener() {
            int judaism_flag = 0;
            @Override
            public void onClick(View view) {
                if (judaism_flag == 0){
                    //食品リストを追加
                    judaism_flag = 1;
                    judaism.setImageResource(R.drawable.judaism_check);
                } else {
                    //食品リストを削除
                    judaism_flag = 0;
                    judaism.setImageResource(R.drawable.judaism);
                }
            }
        });
        ImageButton sinto = (ImageButton)findViewById(R.id.sinto);

    }
}
