package com.example.shirokuma.whatsdish;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import static com.example.shirokuma.whatsdish.ReligionView.religionDataFormatList;

public class ReligionButton extends android.support.v7.widget.AppCompatImageButton{

    private int listID;
    private boolean isSelect = false;
    private String religionName;
    private OnClickListener listener;

    public ReligionButton(Context context) {
        super(context);
    }

    public ReligionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReligionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setValue(int listID, String religionName, boolean isSelect) {
        this.listID = listID;
        this.religionName = religionName;
        this.isSelect = isSelect;
        setImageResource(changeReligionPicture());
    }

    public boolean isSelect() {
        return isSelect;
    }

    private void changeReligionData(int religionNum) {
        //religionDataFormatList.set(0, new ReligionDataFormat("buddhism", true));
    }

    public int changeReligionPicture() {
        int resID;
        if (isSelect) {
            resID = getResources().getIdentifier(religionName + "_check", "drawable", getContext().getPackageName());
        } else {
            resID = getResources().getIdentifier(religionName, "drawable", getContext().getPackageName());
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
                        isSelect = !isSelect;
                        Log.d("weiwei", "63:" + religionName + ":" + isSelect);
                        religionDataFormatList.set(listID, new ReligionDataFormat(religionName, isSelect));
                        Log.d("weiwei", "一覧");
                        for (ReligionDataFormat l : religionDataFormatList) {
                            Log.d("weiwei",l.religionName + ":" + l.isSelect);
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
}
