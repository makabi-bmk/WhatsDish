package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class SetLanguage2 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String[] myCountry = {
            "日本",
            "English",
            "中国語(繁体)",
            "中国語(簡体)"
    };

    private static final String[] countryID = {
            "ja",
            "en",
            "tw",   //繁体=台湾
            "cn"    //簡体=中国
    };
    private String otherLan;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setlanguage2);

        Intent intent = getIntent();

        otherLan = intent.getStringExtra("Language");

        ListView listView = findViewById(R.id.listView);

        BaseAdapter adapter = new Lan_ListViewAdapter(this.getApplicationContext(),
                R.layout.list_language, myCountry);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent = new Intent(this.getApplicationContext(), Question.class);
        String selectedLan = countryID[position];
        intent.putExtra("myLan", selectedLan);
        intent.putExtra("otherLan", otherLan);

        startActivity(intent);

        finish();
    }
}
