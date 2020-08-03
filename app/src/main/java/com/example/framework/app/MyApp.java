package com.example.framework.app;

import android.app.Application;

import com.example.framework.work.API;
import com.example.hwokhttp.HwNetManager;
import com.hjq.image.ImageLoader;

public class MyApp extends MyAppEnable {

public static Application mAppLication;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppLication=this;
        HwNetManager.getHwNetManager().initApi(API.class);
    }

    @Override
    public void initImage() {
        ImageLoader.init(getInstance());
    }

    public static Application getApplication() {
        return mAppLication;
    }
}
