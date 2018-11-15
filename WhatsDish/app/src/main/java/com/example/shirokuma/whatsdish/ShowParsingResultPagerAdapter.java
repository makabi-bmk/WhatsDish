package com.example.shirokuma.whatsdish;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ShowParsingResultPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<DishData> canEatList, cannotEatList;
    private Context mContext;
    private String tab1, tab2;

    ShowParsingResultPagerAdapter(FragmentManager fm, ArrayList<DishData> canEatList, ArrayList<DishData> cannotEatList, Context context, String tab1, String tab2) {
        super(fm);
        this.canEatList = canEatList;
        this.cannotEatList = cannotEatList;
        mContext = context;
        this.tab1 = tab1;
        this.tab2 = tab2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ShowListFragment.newInstance(mContext, canEatList);
            case 1:
                return ShowListFragment.newInstance(mContext, cannotEatList);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            //TODO: ここ多言語化する
            return tab1;
        }
        return tab2;
    }
}
