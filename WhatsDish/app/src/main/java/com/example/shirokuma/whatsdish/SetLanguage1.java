package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class SetLanguage1 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static  final String[] country = {
            "日本",
            "English",
            "中国語(繁体)",
            "中国語(簡体)"
    };

    private static final String[] countryId = {
            "ja",
            "en",
            "tw",
            "cn"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setlanguage1);

        ListView listView = findViewById(R.id.listView);

        BaseAdapter adapter = new Lan_ListViewAdapter(this.getApplicationContext(),
                R.layout.list_language, country);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent = new Intent(this.getApplicationContext(), SetLanguage2.class);
        String selectedLan = countryId[position];
        intent.putExtra("Language", selectedLan);
        startActivity(intent);
        finish();
    }
}
