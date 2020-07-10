package com.example.framework.imageFun;

/**
 * Time       : 2020/07/07 上午10:45
 * Author     : sion
 * Description:
 */
public class HWImageManager {

    private static HWImageManager hwImageManager;
    private String appDirName = "/image";


    public static HWImageManager getInstance() {
        if (hwImageManager == null) {
            synchronized (HWImageManager.class) {
                if (hwImageManager == null) {
                    hwImageManager = new HWImageManager();
                }
            }
        }
        return hwImageManager;
    }

    private ImageUntil imageUntil;
    private ImageBitmapFun bitmapFun;

    public HWImageManager() {
        imageUntil = new ImageUntil();
        bitmapFun = new ImageBitmapFunImpl();
    }

    public ImageUntil getImageUntil() {
        return imageUntil;
    }

    public ImageBitmapFun getBitmapFun() {
        return bitmapFun;
    }

    public String getAppDirName() {
        return appDirName;
    }


    public void setAppDirName(String appDirName) {
        this.appDirName = appDirName;
    }
}
