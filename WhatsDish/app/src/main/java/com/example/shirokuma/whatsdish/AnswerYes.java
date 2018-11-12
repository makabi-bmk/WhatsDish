package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnswerYes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_yes);

        TextView answer = findViewById(R.id.answer_yes);

        Intent intent = getIntent();
        String otherLan = intent.getStringExtra("otherLan");

        Resources res = getResources();
        int otherQuestion = res.getIdentifier("answer1_" + otherLan, "string", getPackageName());

        answer.setText(res.getString(otherQuestion));

        Button close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnswerYes.this, Allergies.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
