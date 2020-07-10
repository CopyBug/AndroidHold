package com.example.framework.app;

import android.app.Application;
import android.view.View;

public abstract class MyAppEnable extends Application {
    private static Application mAppLication;

    public static Application getInstance() {
        return mAppLication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppLication = this;
        initImage();
    }

    //初始化图片加载
    public abstract void initImage();
}
