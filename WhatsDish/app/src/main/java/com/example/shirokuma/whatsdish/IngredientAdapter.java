package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class IngredientAdapter extends ArrayAdapter<IngredientData> {

    private LayoutInflater layoutInflater;
    private ArrayList<IngredientData> ingredientList;
    Context context;

    public IngredientAdapter(Context context, int resourse, ArrayList<IngredientData> items) {
        super(context, resourse, items);
        this.context = context;
        ingredientList = items;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.ingredient_listview, null);

        IngredientData ingredientData = ingredientList.get(position);
        TextView textView = convertView.findViewById(R.id.ingredient_name);
        textView.setText(ingredientData.name);
        Switch mSwitch = convertView.findViewById(R.id.ingredient_switch);

        return convertView;
    }
}
