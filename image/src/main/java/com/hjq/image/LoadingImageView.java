package com.hjq.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.hjq.copy.R;

/**
 * Time       : 2020/07/06 下午3:56
 * Author     : sion
 * Description:
 */
public class LoadingImageView extends AppCompatImageView implements Runnable {

    private boolean stopLoading = true;
    //旋转间隔
    private final int TIME = 80;
    private float offsetArgs = 0;
    private Handler handler;
    private int defalutLoading=R.drawable.ic_loading;
    private Bitmap defalutDrawable;

    public LoadingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        handler=new Handler();
        defalutDrawable=( (BitmapDrawable) getResources().getDrawable(defalutLoading)).getBitmap();
        super.setImageBitmap(defalutDrawable);
        this.setScaleType(ScaleType.CENTER_INSIDE);
        startLoading();
    }

    public void startLoading() {
        stopLoading=false;
        handler.post(this);
    }

    public void stopLoading() {
        stopLoading=true;
        handler.removeCallbacks(this);
        this.setRotation(0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLoading();
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
            super.setImageDrawable(drawable);

    }

    @Override
    public void setImageBitmap(Bitmap bm) {
            super.setImageBitmap(bm);
    }

    @Override
    public void run() {
        if(stopLoading){
            handler.removeCallbacks(this);
        }else{
            this.setRotation(offsetArgs);
            offsetArgs+=40;
            handler.postDelayed(this,TIME);
        }
    }
}
