package com.strekha.lastfm;


import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Dimension;
import android.widget.Toast;

public class UiUtils {

    @Dimension
    public static int getDimension(@DimenRes int resId){
        return (int) LastFmApplication.getInstance().getResources().getDimension(resId);
    }

    @ColorInt//TODO better return  the ColorStateList because of selectors.
    public static int getColor(@ColorRes int color){
        return LastFmApplication.getInstance().getResources().getColor(color);
    }

    public static void showPopup(String message){
        Toast.makeText(LastFmApplication.getInstance(), message, Toast.LENGTH_LONG).show();
    }

}
