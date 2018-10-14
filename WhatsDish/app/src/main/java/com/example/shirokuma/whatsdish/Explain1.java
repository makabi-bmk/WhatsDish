package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Explain1 extends AppCompatActivity {

    private Button nextButton;
    private Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explain1);

        ImageView imageView = (ImageView)findViewById(R.id.image1);
        Button nextButton = (Button)findViewById(R.id.buttonNext1);
        Button skip = (Button)findViewById(R.id.skip);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Explain1.this, Explain2.class);
                startActivity(intent);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Explain1.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
