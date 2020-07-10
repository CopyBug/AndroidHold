package com.example.framework.module;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.ProxyInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.framework.R;
import com.example.framework.databinding.ActImageOpBinding;
import com.example.framework.imageFun.HWImageManager;
import com.hjq.image.ImageLoader;
import com.sjz20200427.base.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Time       : 2020/07/08 上午11:11
 * Author     : sion
 * Description: 图片的处理
 */
public class ImageOpAct extends BaseActivity<ActImageOpBinding> implements View.OnClickListener {
    private final String IMAGE_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594040184515&di=b7806535386ef077df89a95bb3243d51&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fphotoblog%2F1606%2F11%2Fc9%2F22672172_1465635465244.jpg";
    private Button image_save;
    private ImageView my_image;
    private ImageView image_shred1;
    private ImageView image_shred2;
    private ImageView image_merge;
    private Button image_photo;
    private Button image_photo_crop;
    private Button image_photo_merge1;
    private Button image_photo_merge2;
    private Button image_photo_merge3;
    private HorizontalScrollView image_ho_layout;
    private boolean isCrop = false;
    private final int PHOTO_MODE = 999;
    private final int PHOTO_CROP_MODE = 888;
    private Uri cutOut;

    @Override
    protected int getLayoutId() {
        return R.layout.act_image_op;
    }

    @Override
    public boolean isDatabing() {
        return true;
    }

    @Override
    protected void initView() {
        image_save = findViewById(R.id.image_save);
        my_image = findViewById(R.id.my_image);
        image_shred1 = findViewById(R.id.image_shred1);
        image_shred2 = findViewById(R.id.image_shred2);
        image_merge = findViewById(R.id.image_merge);
        image_photo_merge1 = findViewById(R.id.image_photo_merge1);
        image_photo_merge2 = findViewById(R.id.image_photo_merge2);
        image_photo_merge3 = findViewById(R.id.image_photo_merge3);
        image_ho_layout = findViewById(R.id.image_ho_layout);
        image_photo = findViewById(R.id.image_photo);
        image_photo_crop = findViewById(R.id.image_photo_crop);
        image_save.setOnClickListener(this);
        image_photo_crop.setOnClickListener(this);
        image_photo.setOnClickListener(this);
        image_photo_merge1.setOnClickListener(this);
        image_photo_merge2.setOnClickListener(this);
        image_photo_merge3.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        binding.setImageUrl(IMAGE_URL);
    }

    @Override
    protected void doBusiness() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_save:
                //截图并且保存
                shot();
                break;
            case R.id.image_photo:
                //打开相册
                openPhoto();
                break;
            case R.id.image_photo_crop:
                //相册+裁剪
                isCrop = true;
                openPhoto();
                break;
            case R.id.image_photo_merge1:
                //两张图片进行上下合并
                Bitmap bitmap = HWImageManager.getInstance().getBitmapFun().imageMerge(Gravity.BOTTOM, getImageBitmap(image_shred1), getImageBitmap(image_shred2));
                if(bitmap!=null){
                    image_shred1.setVisibility(View.GONE);
                    image_shred2.setVisibility(View.GONE);
                    image_ho_layout.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams layoutParams = image_merge.getLayoutParams();
                    layoutParams.height=bitmap.getHeight();
                    layoutParams.width=bitmap.getWidth();
                    image_merge.setLayoutParams(layoutParams);
                    ImageLoader.with(this)
                            .load(bitmap)
                            .into(image_merge);
                }
                break;
            case R.id.image_photo_merge2:
                Bitmap bitmap_bottom = HWImageManager.getInstance().getBitmapFun().imageMerge(Gravity.LEFT, getImageBitmap(image_shred1), getImageBitmap(image_shred2));
                if(bitmap_bottom!=null){
                    image_shred1.setVisibility(View.GONE);
                    image_shred2.setVisibility(View.GONE);
                    image_ho_layout.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams layoutParams = image_merge.getLayoutParams();
                    layoutParams.height=bitmap_bottom.getHeight();
                    layoutParams.width=bitmap_bottom.getWidth();
                    image_merge.setLayoutParams(layoutParams);
                    ImageLoader.with(this)
                            .load(bitmap_bottom)
                            .into(image_merge);
                }
                break;
            case R.id.image_photo_merge3:
                Bitmap imageBitmap = getImageBitmap(image_shred1);
                //图片融合
                Bitmap bg = Bitmap.createBitmap(imageBitmap.getWidth(), imageBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bg);
                canvas.drawColor(Color.parseColor("#80000000"));
                Bitmap bitmap_center = HWImageManager.getInstance().getBitmapFun().imageMerge(Gravity.CENTER, imageBitmap, bg);
                if(bitmap_center!=null){
                    image_shred1.setVisibility(View.GONE);
                    image_shred2.setVisibility(View.GONE);
                    image_ho_layout.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams layoutParams = image_merge.getLayoutParams();
                    layoutParams.height=imageBitmap.getHeight();
                    layoutParams.width=imageBitmap.getWidth();
                    image_merge.setLayoutParams(layoutParams);
                    ImageLoader.with(this)
                            .load(bitmap_center)
                            .circle(30)
                            .into(image_merge);
                }
                break;


        }
    }

    public Bitmap getImageBitmap(ImageView imageView){
       return  ((BitmapDrawable)imageView.getDrawable()).getBitmap();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        switch (requestCode) {
            case PHOTO_MODE:
                //相册
                if (data != null && data.getData() != null) {
                    Uri uri = data.getData();
                    if (isCrop) {
                        isCrop = false;
                        cutOut = cropPhoto(uri);
                    } else {
                        ImageLoader.with(this)
                                .load(uri)
                                .circle(20)
                                .into(my_image);
                    }
                }
                break;
            case PHOTO_CROP_MODE:
                //图片裁剪
                if (cutOut != null) {
                    ImageLoader.with(this)
                            .load(cutOut)
                            .circle(20)
                            .into(my_image);
                }

                break;
        }
    }

    private Uri cropPhoto(Uri uri) {
        return HWImageManager.getInstance().getBitmapFun().imageCutOut(this, uri, PHOTO_CROP_MODE, 30, 40, 500, 800);
    }

    private void openPhoto() {
        HWImageManager.getInstance().getBitmapFun().imageOpenPhoto(this, PHOTO_MODE);
    }

    private void shot() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean rxResult) throws Exception {
                        if (rxResult) {
                            //截图并且保存
                            Bitmap bitmap = HWImageManager.getInstance().getBitmapFun().imageShot(my_image);
                            HWImageManager.getInstance().getBitmapFun().imageSaveRx(bitmap, "测试", ImageOpAct.this)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<File>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {
                                        }

                                        @Override
                                        public void onNext(File file) {
                                            Toast.makeText(ImageOpAct.this, "保存成功", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                        } else {
                            Toast.makeText(ImageOpAct.this, "请打开读写权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


}
