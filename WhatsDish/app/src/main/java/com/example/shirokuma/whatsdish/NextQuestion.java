package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.shirokuma.whatsdish.Allergies.allergiesDataFormatList;

public class NextQuestion extends AppCompatActivity {

    private StringBuilder allergiesList;
    private String otherLan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_question);

        TextView question = findViewById(R.id.question);
        TextView allergyList = findViewById(R.id.allergy_list);
        Button close = findViewById(R.id.close);

        //言語を受け取って質問をsetText
        Intent intent = getIntent();
        otherLan = intent.getStringExtra("otherLan");

        Resources res = getResources();
        int otherQuestion = res.getIdentifier("question2_" + otherLan, "string", getPackageName());

        question.setText(res.getString(otherQuestion));

        setAllergiesList();
        allergyList.setText(allergiesList);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NextQuestion.this, Allergies.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setAllergiesList() {

        Resources res = getResources();
        allergiesList = new StringBuilder();
        for (AllergiesDataFormat l : allergiesDataFormatList) {
            if (l.isSelect) {
                    int allergen = res.getIdentifier(l.allergiesName + "_" + otherLan, "string", getPackageName());
                    allergiesList.append("・" + res.getString(allergen) + "\n");
            }
        }
    }
}
