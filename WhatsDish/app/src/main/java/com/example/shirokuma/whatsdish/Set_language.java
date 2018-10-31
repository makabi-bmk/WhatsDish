package com.example.shirokuma.whatsdish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class Set_language extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String[] lan = {
            "Japan", "China", "Taiwan"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_language);

        ListView listView = findViewById(R.id.list_lan);

        BaseAdapter adapter = new ListViewAdapter(this.getApplicationContext(),
                R.layout.list_setlan, lan);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//        どの項目がクリックされたかで条件分岐して
//        putExtraでLocaleにいれる値を投げる
    }
}
