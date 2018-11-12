package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ShowParsingResultPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<FoodData2> canEatList, cannotEatList;
    private Context mContext;

    ShowParsingResultPagerAdapter(FragmentManager fm, ArrayList<FoodData2> canEatList,ArrayList<FoodData2> cannotEatList, Context context) {
        super(fm);
        this.canEatList = canEatList;
        this.cannotEatList = cannotEatList;
        mContext = context;
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
            return "食べられるもの";
        }
        return "食べられないもの";
    }
}
