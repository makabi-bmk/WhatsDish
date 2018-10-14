package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Explain3 extends AppCompatActivity {

    private Button beforeButton;
    private Button startButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explain3);


        Button beforeButton = (Button)findViewById(R.id.buttonBefore2);
        Button startButton = (Button)findViewById(R.id.start);

        beforeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Explain3.this, Explain2.class);
                startActivity(intent);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Explain3.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
