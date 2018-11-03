package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.shirokuma.whatsdish.Allergies.allergiesDataFormatList;

public class Question extends AppCompatActivity {

    private String allergiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        TextView questionOther = findViewById(R.id.othQuestion);
        TextView questionMy = findViewById(R.id.myQuestion);

        Intent intent = getIntent();
        String otherLan = intent.getStringExtra("otherLan");
        String myLan = intent.getStringExtra("myLan");


        Resources res = getResources();
        int otherQuestion = res.getIdentifier("question_" + otherLan, "string", getPackageName());
        int myQuestion = res.getIdentifier("question_" + myLan, "string", getPackageName());

        questionOther.setText(res.getString(otherQuestion));
        questionMy.setText(res.getString(myQuestion));

        setAllergiesList();
        TextView allergies = findViewById(R.id.myAllergen);
        allergies.setText(allergiesList);
    }

    public void setAllergiesList() {
        for (AllergiesDataFormat l : allergiesDataFormatList) {
            if (l.isSelect) {
                if (allergiesList == null) {
                    allergiesList = "・" + l.allergiesName + "\n";
                }
                else {
                    allergiesList += "・" + l.allergiesName + "\n";
                }
            }
        }
    }
}
