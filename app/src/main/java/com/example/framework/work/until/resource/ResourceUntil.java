package com.example.framework.work.until.resource;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;


/**资源工具*/
public class ResourceUntil {

    public static int getColor(Context mContext, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //大于23
            return ContextCompat.getColor(mContext, color);
        } else {
            return mContext.getResources().getColor(color);
        }
    }

    public static int getColor(String color) {
        return Color.parseColor(color);
    }

    /**
     * alpha 表示透明度，0表示透明，255 表示不透明
     */
    public static int getColor(int alpha, float r, float g, float b) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Color.argb(alpha, r, g, b);
        }
        return 000000;
    }

    public static String getString(Context mContext, int str) {
        return mContext.getString(str);
    }

    public static Drawable getDrawable(Context mContext, int draw) {
        return mContext.getDrawable(draw);
    }

    public static int getRes(Context mContext, String type, String name) {
        return mContext.getResources().getIdentifier(name, type, mContext.getPackageName());
    }

}
