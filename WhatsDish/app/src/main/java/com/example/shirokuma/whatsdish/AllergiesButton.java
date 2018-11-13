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
        //エビ、車エビ
        categoryList.put(0, new ArrayList<>(Arrays.asList(132, 123)));
        //カニ
        categoryList.put(1, new ArrayList<>(Arrays.asList(124)));
        //そば
        categoryList.put(2, new ArrayList<>(Arrays.asList(176)));
        //小麦
        categoryList.put(3, new ArrayList<>(Arrays.asList(173)));
        //卵
        categoryList.put(4, new ArrayList<>(Arrays.asList(192)));
        //牛乳
        categoryList.put(5, new ArrayList<>(Arrays.asList(193)));
        //ピーナッツ
        categoryList.put(6, new ArrayList<>(Arrays.asList(179)));
        //イカ
        categoryList.put(7, new ArrayList<>(Arrays.asList(126)));
        //いくら
        categoryList.put(8, new ArrayList<>(Arrays.asList(128)));
        //オレンジ
        categoryList.put(9, new ArrayList<>(Arrays.asList(110)));
        //カシューナッツ,ピスタチオ
        categoryList.put(10, new ArrayList<>(Arrays.asList(184, 185)));
        //キウイ
        categoryList.put(11, new ArrayList<>(Arrays.asList(98)));
        //牛
        categoryList.put(12, new ArrayList<Integer>(Arrays.asList(111)));
        //くるみ
        categoryList.put(13, new ArrayList<Integer>(Arrays.asList(93)));
        //ごま
        categoryList.put(14, new ArrayList<Integer>(Arrays.asList(187)));
        //魚
        categoryList.put(15, new ArrayList<Integer>(Arrays.asList(122)));
        //大豆
        categoryList.put(16, new ArrayList<Integer>(Arrays.asList(177)));
        //鶏肉
        categoryList.put(17, new ArrayList<Integer>(Arrays.asList(113)));
        //バナナ
        categoryList.put(18, new ArrayList<Integer>(Arrays.asList(97)));
        //豚肉
        categoryList.put(20, new ArrayList<Integer>(Arrays.asList(112)));
        //えのき茸,しいたけ,しめじ茸,なめこ,松茸,マッシュルーム,エリンギ,キクラゲ,マイタケ
        categoryList.put(19, new ArrayList<Integer>(Arrays.asList(2, 13, 15, 27, 47, 58, 68, 69, 70)));
        //桃
        categoryList.put(21, new ArrayList<Integer>(Arrays.asList(82)));
        //やまいも
        categoryList.put(22, new ArrayList<Integer>(Arrays.asList(38)));
        //りんご
        categoryList.put(23, new ArrayList<Integer>(Arrays.asList(78)));
        //ゼラチン
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

