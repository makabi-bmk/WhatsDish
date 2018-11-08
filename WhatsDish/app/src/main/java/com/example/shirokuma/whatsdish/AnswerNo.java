package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnswerNo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_no);

        TextView answer = findViewById(R.id.answer_no);

        Intent intent = getIntent();
        final String otherLan = intent.getStringExtra("otherLan");

        Resources res = getResources();
        int otherAnswer = res.getIdentifier("answer2_" + otherLan, "string", getPackageName());

        answer.setText(res.getString(otherAnswer));


        Button next = findViewById(R.id.next_question);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnswerNo.this, NextQuestion.class);
                startActivity(intent);
            }
        });
    }
}
