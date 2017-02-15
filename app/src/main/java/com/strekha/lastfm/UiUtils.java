package com.strekha.lastfm;


import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class UiUtils {

    @Dimension
    public static int getDimension(@DimenRes int resId){
        return (int) LastFmApplication.getInstance().getResources().getDimension(resId);
    }

    public static ColorStateList getColorStateList(@ColorRes int colorStateList){
        return ContextCompat.getColorStateList(LastFmApplication.getInstance(), colorStateList);
    }

    public static void showPopup(String message){
        Toast.makeText(LastFmApplication.getInstance(), message, Toast.LENGTH_LONG).show();
    }

}
