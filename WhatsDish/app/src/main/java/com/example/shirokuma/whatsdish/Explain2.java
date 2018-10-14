package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Explain2 extends AppCompatActivity {

    private Button beforeButton;
    private Button nextButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explain2);



        Button beforeButton = (Button)findViewById(R.id.buttonBefore1);
        Button nextButton = (Button)findViewById(R.id.buttonNext2);

        beforeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Explain2.this, Explain1.class);
                startActivity(intent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Explain2.this, Explain3.class);
                startActivity(intent);
            }
        });

    }
}
