package com.example.shirokuma.whatsdish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.shirokuma.whatsdish.IngredientListFormat.categories;
import static com.example.shirokuma.whatsdish.IngredientListFormat.categoryNames;
import static java.sql.Types.NULL;

public class ShowIngredientList2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_ingredientlist2);

        int categoryNum = getIntent().getIntExtra("categoryNum", -1);
        final ListView listView = findViewById(R.id.ingredient_list2);
        ArrayList<IngredientListFormat> list = new ArrayList<>();

        int ingredientNum = 0;
        while (true) {
            int strID = getResources().getIdentifier( categoryNames[categoryNum] + "_ja_" + ingredientNum, "string", getPackageName());
            if (strID == NULL || strID == 0) {
                break;
            }
            Log.d("weiwei", "strID = " + categoryNames[categoryNum] + "_ja_" + ingredientNum);
            Log.d("weiwei", "str = " + getResources().getString(strID));
            list.add(new IngredientListFormat(getResources().getString(strID), categories[categoryNum]));
            ingredientNum++;
        }

        IngredientAdapter adapter = new IngredientAdapter(this, R.layout.ingredient_listview, list);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (listView.getCheckedItemPositions().valueAt(position)) {
//                    //TODO:チャックリストに追加する文を書く
//                    Log.d("weiwei", "このアイテムが選択されたよ！→" + listView.getItemAtPosition(position));
//                }
//            }
//        });
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
