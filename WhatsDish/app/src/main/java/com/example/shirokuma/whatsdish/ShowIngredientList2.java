package com.example.shirokuma.whatsdish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.shirokuma.whatsdish.IngredientData.categories;
import static com.example.shirokuma.whatsdish.IngredientData.categoryNames;
import static com.example.shirokuma.whatsdish.MainActivity.ingredientFile;
import static java.sql.Types.NULL;

public class ShowIngredientList2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_ingredientlist2);

        int num = getIntent().getIntExtra("categoryNum", -1);
        final ListView listView = findViewById(R.id.ingredient_list2);
        ArrayList<IngredientData> list = new ArrayList<>();

        int i = 0;
        while (true) {
            int strID = getResources().getIdentifier( categoryNames[num] + "_" + i, "string", getPackageName());
            if (strID == NULL || strID == 0) {
                break;
            }
            list.add(new IngredientData(getResources().getString(strID), categories[num]));
            i++;
        }

        IngredientAdapter adapter = new IngredientAdapter(this, R.layout.ingredient_listview, list, File.firstElementNumbers[num]);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ingredientFile.saveData();
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
