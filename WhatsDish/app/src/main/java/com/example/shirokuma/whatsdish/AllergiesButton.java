package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import static com.example.shirokuma.whatsdish.Allergies.allergyFile;


public class AllergiesButton extends android.support.v7.widget.AppCompatImageButton {

    private String name;
    private int position;
    private OnClickListener listener;

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

}

