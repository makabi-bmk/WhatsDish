package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ShowParsingResult2 extends AppCompatActivity
{
    ArrayList<FoodData2> foodData2s;
    Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setFoodData2s(ArrayList<FoodData2> foodData2s) {
        this.foodData2s = foodData2s;
    }

    public void setInfo() {
        final ListView listView = findViewById(R.id.list_view2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1);

        for(FoodData2 f : foodData2s) {
            Log.d("weiwei", "onCreateView:foodData = " + f.getFoodName());
            adapter.add(f.getFoodName());
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
