package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<IngredientListFormat> ingredientList;
    Context context;

    public IngredientAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setIngredientList(ArrayList<IngredientListFormat> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @Override
    public int getCount() {
        return this.ingredientList.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredientList.get(position);
    }

    @Override
    public long getItemId(int position) {

        //return ingredientList.getListID();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.ingredient_listview, parent, false);
        ((TextView)convertView.findViewById(R.id.ingredient_name)).setText(ingredientList.get(position).getName());
        ((Switch)convertView.findViewById(R.id.ingredient_switch)).setChecked(ingredientList.get(position).isPossibleToEat());
        return convertView;
    }
}
