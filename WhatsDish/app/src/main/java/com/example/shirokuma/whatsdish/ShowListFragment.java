package com.example.shirokuma.whatsdish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    ArrayList<DishData> dishData;
    ArrayList<Integer> foodID;

    public static ShowListFragment newInstance(Context context, ArrayList<DishData> dishData) {
        ShowListFragment fragment = new ShowListFragment();
        fragment.mContext = context;
        fragment.dishData = dishData;
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
        foodID = new ArrayList<>();

        for(DishData f : dishData) {
            int strID = getResources().getIdentifier("food_name_var_" + f.getID(), "string", getActivity().getPackageName());
            adapter.add(getResources().getString(strID));
            foodID.add(f.getID());
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ShowFoodInfo.class);
                intent.putExtra("foodData", foodID.get(position));
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}
