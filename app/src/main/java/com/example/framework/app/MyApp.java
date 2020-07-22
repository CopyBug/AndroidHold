package com.example.framework.app;

import com.example.framework.work.API;
import com.example.hwokhttp.HwNetManager;
import com.hjq.image.ImageLoader;

public class MyApp extends MyAppEnable {


    @Override
    public void onCreate() {
        super.onCreate();
        HwNetManager.getHwNetManager().initApi(API.class);
    }

    @Override
    public void initImage() {
        ImageLoader.init(getInstance());
    }
}
