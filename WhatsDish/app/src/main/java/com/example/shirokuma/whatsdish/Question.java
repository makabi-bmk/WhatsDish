package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.shirokuma.whatsdish.MainActivity.allergyFile;

public class Question extends AppCompatActivity {

    private StringBuilder allergiesList;
    private String otherLan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        TextView questionOther = findViewById(R.id.othQuestion);
        TextView questionMy = findViewById(R.id.myQuestion);
        Button yesButton = findViewById(R.id.yes_button);
        Button noButton = findViewById(R.id.no_button);

        Intent intent = getIntent();
        otherLan = intent.getStringExtra("otherLan");
        final String myLan = intent.getStringExtra("myLan");


        Resources res = getResources();
        int otherQuestion = res.getIdentifier("question_" + otherLan, "string", getPackageName());
        int myQuestion = res.getIdentifier("question_" + myLan, "string", getPackageName());
        int yes = res.getIdentifier("yes_" + otherLan, "string", getPackageName());
        int no = res.getIdentifier("no_" + otherLan, "string", getPackageName());

        yesButton.setText(res.getString(yes));
        noButton.setText(res.getString(no));

        questionOther.setText(res.getString(otherQuestion));
        questionMy.setText(res.getString(myQuestion));

        setAllergiesList();
        TextView allergies = findViewById(R.id.myAllergen);
        allergies.setText(allergiesList);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Question.this, AnswerYes.class);
                intent.putExtra("otherLan", otherLan);
                startActivity(intent);
                finish();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Question.this, AnswerNo.class);
                intent.putExtra("otherLan", otherLan);
                intent.putExtra("myLan", myLan);
                startActivity(intent);
                finish();

            }
        });
    }


    public void setAllergiesList() {
        allergiesList = new StringBuilder();
        Resources res = getResources();
        for (Data l : allergyFile.getList()) {
            if (l.isSelect) {
                int allergen = res.getIdentifier(l.name + "_" + otherLan, "string", getPackageName());
                allergiesList.append("・" + res.getString(allergen) + "\n");
            }
        }
    }
}
