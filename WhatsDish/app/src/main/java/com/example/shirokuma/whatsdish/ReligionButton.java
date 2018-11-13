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

import static com.example.shirokuma.whatsdish.MainActivity.ingredientFile;
import static com.example.shirokuma.whatsdish.Religion.religionFile;

public class ReligionButton extends android.support.v7.widget.AppCompatImageButton{

    private String name;
    private int position;
    private OnClickListener listener;
    HashMap<Integer, ArrayList<Integer>> categoryList = new HashMap<>();

    public ReligionButton(Context context) {
        super(context);
    }

    public ReligionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReligionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setValue(int position) {
        this.position = position;
        this.name = religionFile.getData(position).name;
        setImageResource(changeReligionPicture());
        initData();
    }

    public boolean isSelect() {
        return religionFile.getData(position).isSelect;
    }

    public int changeReligionPicture() {
        int resID;
        if (religionFile.getData(position).isSelect) {
            resID = getResources().getIdentifier(name + "_check", "drawable", getContext().getPackageName());
        } else {
            resID = getResources().getIdentifier(name, "drawable", getContext().getPackageName());
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
                        religionFile.changeSelect(position);
                        if (religionFile.getData(position).isSelect) {
                            changeSelectToTrue(position);
                        } else {
                            changeSelectToFalse(position);
                        }
                        setImageResource(changeReligionPicture());
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
        //ヒンドゥー教, 肉全般, 魚介類全般, 卵
        categoryList.put(2, new ArrayList<Integer>());
        for (int i = 111; i <= 139; i++) {
            categoryList.get(2).add(i);
        }
        categoryList.get(2).add(192);
        //イスラム教, 豚肉, みりん, 料理酒
        categoryList.put(3, new ArrayList<Integer>(Arrays.asList(112, 169, 170)));
    }

    void changeSelectToTrue(int elementNum) {
        if (categoryList.containsKey(elementNum)) {
            for (int i : categoryList.get(elementNum)) {
                Log.d("weiwei", "num = " + i);
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
