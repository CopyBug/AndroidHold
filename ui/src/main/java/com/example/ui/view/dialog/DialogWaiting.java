package com.example.ui.view.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.example.ui.R;
import com.sjz20200427.base.BaseDialog;

import java.net.PortUnreachableException;

public class DialogWaiting {

    private static  DialogWaiting dialogWaiting;
    private Builder builder;
    public static DialogWaiting getInstance() {
        if(dialogWaiting==null){
            synchronized (DialogWaiting.class){
                if(dialogWaiting==null){
                    dialogWaiting=new DialogWaiting();
                }
            }
        }
        return dialogWaiting;
    }

    public void showDialog(Context mContext,boolean isCancel,String context) {
        builder=new Builder(mContext);
        builder.setCanceledOnTouchOutside(isCancel);
        builder.setMessage(context);
        builder.show();
    }
    public void showDialog(Context mContext) {
        showDialog(mContext,false,"加载中");
    }
    public void showDialog(Context mContext,String context) {
        showDialog(mContext,false,context);
    }

    public void showDialog(Context mContext,boolean isCancel) {
        showDialog(mContext,isCancel,"加载中");
    }
    public void hideDialog(){
        if(builder!=null&&builder.isShowing()){
            builder.dismiss();
            builder=null;
        }
    }

    private  final class Builder
            extends BaseDialog.Builder<Builder> {

        private final TextView mMessageView;
        public Builder(Context context) {
            super(context);
            setContentView(R.layout.dialog_wait);
            setAnimStyle(BaseDialog.ANIM_TOAST);
            setBackgroundDimEnabled(false);
//            setCancelable(false);
            setCanceledOnTouchOutside(false);
            mMessageView = findViewById(R.id.tv_wait_message);
        }


        public Builder setMessage(@StringRes int id) {
            return setMessage(getContext().getString(id));
        }

        public Builder setMessage(CharSequence text) {
            mMessageView.setText(text);
            mMessageView.setVisibility(text == null ? View.GONE : View.VISIBLE);
            return this;
        }
    }
}
