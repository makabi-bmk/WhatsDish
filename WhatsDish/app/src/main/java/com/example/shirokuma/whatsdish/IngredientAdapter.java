package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.shirokuma.whatsdish.MainActivity.ingredientFile;

public class IngredientAdapter extends ArrayAdapter<IngredientData> {

    private LayoutInflater layoutInflater;
    private ArrayList<IngredientData> ingredientList;
    Context context;
    private int firstElementNum;

    public IngredientAdapter(Context context, int resourse, ArrayList<IngredientData> items, int firstElementNum) {
        super(context, resourse, items);
        this.context = context;
        ingredientList = items;
        this.firstElementNum = firstElementNum;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.ingredient_listview, null);

        final int elementNum = firstElementNum + position;

        IngredientData ingredientData = ingredientList.get(position);
        TextView textView = convertView.findViewById(R.id.ingredient_name);
        textView.setText(ingredientData.name);

        Switch mSwitch = convertView.findViewById(R.id.ingredient_switch);
        mSwitch.setChecked(ingredientFile.getIngredientData(elementNum).isSelect);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ingredientFile.changeSelect(elementNum);
            }
        });
        return convertView;
    }
}
