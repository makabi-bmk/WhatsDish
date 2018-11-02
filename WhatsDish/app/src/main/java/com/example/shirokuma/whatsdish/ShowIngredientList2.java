package com.example.shirokuma.whatsdish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static java.sql.Types.NULL;

public class ShowIngredientList2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_ingredientlist2);

        int categoryNum = getIntent().getIntExtra("categoryNum", -1);
        //String categoryName = getResources().getString(getResources().getIdentifier("category_" + categoryNum, "string", getPackageName()));
        String[] categoryName = {"vegetables", "fruits", "meats", "seafoods", "seasonings", "grains", "dairy_products"};
        IngredientListFormat.Category[] category = {IngredientListFormat.Category.vegetable, IngredientListFormat.Category.fruit, IngredientListFormat.Category.meat, IngredientListFormat.Category.seafood, IngredientListFormat.Category.seasoning, IngredientListFormat.Category.seasoning, IngredientListFormat.Category.grain, IngredientListFormat.Category.dairy_product};
        ArrayList<IngredientListFormat> list = new ArrayList<IngredientListFormat>();

        Log.d("weiwei", "categoryNum = " + categoryNum);
        Log.d("weiwei", "categoryName = " + categoryName[categoryNum]);
        final ListView listView = findViewById(R.id.ingredient_list);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice);
        IngredientAdapter adapter = new IngredientAdapter(ShowIngredientList2.this);
        int ingredientNum;

        ingredientNum = 0;
        while (true) {
            int strID = getResources().getIdentifier( categoryName[categoryNum] + "_ja_" + ingredientNum, "string", getPackageName());
            if (strID == NULL || strID == 0) {
                break;
            }
            IngredientListFormat ingredientListFormat = new IngredientListFormat(0, getResources().getString(strID), category[categoryNum]);
            list.add(ingredientListFormat);
            ingredientNum++;
        }
        adapter.setIngredientList(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listView.getCheckedItemPositions().valueAt(position)) {
                    //TODO:チャックリストに追加する文を書く
                    Log.d("weiwei", "このアイテムが選択されたよ！→" + listView.getItemAtPosition(position));
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return false;
    }
}
