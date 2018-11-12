package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ShowParsingResult2 extends AppCompatActivity
{
    ArrayList<DishData> dishData;
    Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setDishData(ArrayList<DishData> dishData) {
        this.dishData = dishData;
    }

    public void setInfo() {
        final ListView listView = findViewById(R.id.list_view2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1);

        for(DishData f : dishData) {
            Log.d("weiwei", "onCreateView:foodData = " + f.getName());
            adapter.add(f.getName());
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ShowFoodInfo.class);
                intent.putExtra("foodData", (String) listView.getItemAtPosition(position));
                mContext.startActivity(intent);
            }
        });
    }
}
