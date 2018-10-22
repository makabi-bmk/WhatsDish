package com.example.shirokuma.whatsdish;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.Objects;

public  class LoadingDialogFragment extends DialogFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Dialogの縦幅と横幅を指定
        Dialog dialog = getDialog();
        WindowManager.LayoutParams layoutParams = Objects.requireNonNull(dialog.getWindow()).getAttributes();

        DisplayMetrics metrics = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float dialogwidth = 300 * metrics.scaledDensity;
        float dialogheight = 300 * metrics.scaledDensity;
        layoutParams.width = (int)dialogwidth;
        layoutParams.height = (int)dialogheight;

        dialog.getWindow().setAttributes(layoutParams);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Dialogのlayoutを指定
        View v = inflater.inflate(R.layout.fragment_dialog_loading, container, false);
        ImageView imageView = v.findViewById(R.id.viewGIF);
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.drawable.loading).into(target);
        return v;
    }
}