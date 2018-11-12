package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowListFragment extends Fragment {

    Context mContext;
    ArrayList<FoodData2> foodData2s;
    ArrayList<Integer> amazon;

    public static ShowListFragment newInstance(Context context, ArrayList<FoodData2> foodData2s) {
        ShowListFragment fragment = new ShowListFragment();
        fragment.mContext = context;
        fragment.foodData2s = foodData2s;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmant_main_linearlayout, null);
        LinearLayout linearLayout = view.findViewById(R.id.fragment_main_linearlayout);

        final ListView listView = view.findViewById(R.id.list_view2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1);
        amazon = new ArrayList<>();

        for(FoodData2 f : foodData2s) {
            Log.d("weiwei", "onCreateView:foodData = " + f.getFoodName());
            adapter.add(f.getFoodName());
            amazon.add(f.getID());
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ShowFoodInfo.class);
                intent.putExtra("foodData", amazon.get(position));
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}
