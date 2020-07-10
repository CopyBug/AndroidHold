package com.example.framework.app;

import com.hjq.image.ImageLoader;

public class MyApp extends MyAppEnable {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void initImage() {
        ImageLoader.init(getInstance());
    }
}
