package com.example.framework.work.until.resource;

import android.content.Context;
import android.widget.Toast;

/**吐士*/
public class ToastUntil {

    public static void toastBottom(Context mContext,int res){
        Toast.makeText(mContext, res, Toast.LENGTH_SHORT).show();
    }

    public static void toastBottom(Context mContext,String resChar){
        if(resChar!=null){
            Toast.makeText(mContext, resChar, Toast.LENGTH_SHORT).show();
        }
    }

    public static void toastError(Context mContext,String errorMsg){

    }

}
