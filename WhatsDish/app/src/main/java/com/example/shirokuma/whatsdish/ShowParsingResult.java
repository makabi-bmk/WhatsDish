package com.example.shirokuma.whatsdish;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import java.util.ArrayList;

public class ShowParsingResult extends AppCompatActivity  {

    private boolean isCanEat = true;
    private ArrayList<DishData> canEatList;
    private ArrayList<DishData> cannotEatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_parsing_result);

        initData();
        setViews();
    }

    private void initData() {
        canEatList = new ArrayList<>();
        cannotEatList = new ArrayList<>();

        Intent intent = getIntent();
        ArrayList<Integer> foodID = intent.getIntegerArrayListExtra("foodID");

        for (int ID : foodID) {
            DishData dishData = new DishData(ID, getApplicationContext());
            dishData.setCannotEatIgredientList();
            if (dishData.isCanEat()) {
                canEatList.add(dishData);
            } else {
                cannotEatList.add(dishData);
            }
        }

    }

    private void setViews() {
        FragmentManager manager = getSupportFragmentManager();
        ViewPager viewPager = findViewById(R.id.view_pager);
        ShowParsingResultPagerAdapter adapter = new ShowParsingResultPagerAdapter(manager, canEatList, cannotEatList, getApplicationContext());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return false;
    }

}
