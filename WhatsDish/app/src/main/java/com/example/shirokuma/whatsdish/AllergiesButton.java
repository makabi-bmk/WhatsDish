package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static com.example.shirokuma.whatsdish.MainActivity.allergyFile;
import static com.example.shirokuma.whatsdish.MainActivity.ingredientFile;


public class AllergiesButton extends android.support.v7.widget.AppCompatImageButton {

    private String name;
    private int position;
    private OnClickListener listener;
    HashMap<Integer, ArrayList<Integer>> categoryList = new HashMap<>();

    public AllergiesButton(Context context) {
        super(context);

    }

    public AllergiesButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AllergiesButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setValue(int position) {
        this.position = position;
        this.name = allergyFile.getData(position).name;
        setImageResource(changeAllergiesPicture());
        initData();
    }

    public boolean isSelect() {
        return allergyFile.getData(position).isSelect;
    }

    public int changeAllergiesPicture() {
        int resID;
        if (allergyFile.getData(position).isSelect) {
            resID = getResources().getIdentifier(name + "_a", "drawable", getContext().getPackageName());
        } else {
            resID = getResources().getIdentifier(name + "_b", "drawable", getContext().getPackageName());
        }
        return resID;
    }

    @Override
    public void setOnClickListener(@Nullable View.OnClickListener l) {
        this.listener = l;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // 範囲内のチェックを追加
            if (listener != null && checkInside(event)) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        allergyFile.changeSelect(position);
                        if (allergyFile.getData(position).isSelect) {
                            changeSelectToTrue(position);
                        } else {
                            changeSelectToFalse(position);
                        }
                        setImageResource(changeAllergiesPicture());
                    }
                });
            }
        }
        return super.dispatchTouchEvent(event);
    }

    private boolean checkInside(MotionEvent ev) {
        int[] point = new int[2];

        getLocationOnScreen(point);

        int x = point[0];
        int y = point[1];

        return (ev.getRawX() >= x && ev.getRawX() <= x + getWidth()) &&
                (ev.getRawY() >= y && ev.getRawY() <= y + getHeight());
    }

    @Override
    public void setPressed(boolean pressed){
        if (pressed) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    1.0f, 0.6f / 1.0f, 1.0f, 0.6f / 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
            );
            scaleAnimation.setDuration(150);
            scaleAnimation.setRepeatCount(0);
            scaleAnimation.setFillAfter(true);

            this.startAnimation(scaleAnimation);
        } else {
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    0.6f / 1.0f, 1.0f, 0.6f / 1.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
            );

            scaleAnimation.setDuration(150);
            scaleAnimation.setRepeatCount(0);
            scaleAnimation.setFillAfter(true);

            this.startAnimation(scaleAnimation);
        }

        super.setPressed(pressed);
    }

    void initData() {
        categoryList.put(0, new ArrayList<>(Arrays.asList(123, 132)));
        categoryList.put(1, new ArrayList<>(Arrays.asList(124)));
        categoryList.put(2, new ArrayList<>(Arrays.asList(174)));
        categoryList.put(3, new ArrayList<>(Arrays.asList(171)));
        categoryList.put(4, new ArrayList<>(Arrays.asList(190)));
        categoryList.put(5, new ArrayList<>(Arrays.asList(191)));
        categoryList.put(6, new ArrayList<>(Arrays.asList(177)));
        categoryList.put(7, new ArrayList<>(Arrays.asList(126)));
        categoryList.put(8, new ArrayList<>(Arrays.asList(128)));
        categoryList.put(9, new ArrayList<>(Arrays.asList(110)));
        categoryList.put(10, new ArrayList<>(Arrays.asList(182, 183)));
        categoryList.put(11, new ArrayList<>(Arrays.asList(98)));
        categoryList.put(12, new ArrayList<Integer>(Arrays.asList(122)));
        categoryList.put(13, new ArrayList<Integer>(Arrays.asList(94)));
        categoryList.put(14, new ArrayList<Integer>(Arrays.asList(185)));
        categoryList.put(15, new ArrayList<Integer>(Arrays.asList(122)));
        categoryList.put(16, new ArrayList<Integer>(Arrays.asList(175)));
        categoryList.put(17, new ArrayList<Integer>(Arrays.asList(171)));
        categoryList.put(18, new ArrayList<Integer>(Arrays.asList(97)));
        categoryList.put(20, new ArrayList<Integer>(Arrays.asList(112)));
        categoryList.put(19, new ArrayList<Integer>(Arrays.asList(2, 13, 15, 27, 47, 58, 68, 69, 70)));
        categoryList.put(21, new ArrayList<Integer>(Arrays.asList(82)));
        categoryList.put(22, new ArrayList<Integer>(Arrays.asList(38)));
        categoryList.put(23, new ArrayList<Integer>(Arrays.asList(78)));
    }

    void changeSelectToTrue(int elementNum) {
        if (categoryList.containsKey(elementNum)) {
            for (int i : categoryList.get(elementNum)) {
                Log.d("weiwei", "num = " + i + ", name = " + ingredientFile.getIngredientData(i).name);
                ingredientFile.changeSelectToTrue(i);
            }
        }
    }

    void changeSelectToFalse(int elementNum) {
        if (categoryList.containsKey(elementNum)) {
            for (int i : categoryList.get(elementNum)) {
                ingredientFile.changeSelectToFalse(i);
            }
        }
    }

}

