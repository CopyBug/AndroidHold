package com.example.hwokhttp;

/**
 * Time       : 2020/07/10 上午11:19
 * Author     : sion
 * Description:
 */
public class HwNetManager {
    private static HwNetManager hwNetManager;

    public static HwNetManager getHwNetManager() {
        if(hwNetManager==null){
            synchronized (HwNetManager.class){
                if(hwNetManager==null){
                    hwNetManager=new HwNetManager();
                }
            }
        }
        return hwNetManager;
    }

}
