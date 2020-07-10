package com.example.framework.imageFun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.framework.module.ImageOpAct;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Time       : 2020/07/07 上午10:41
 * Author     : sion
 * Description: 图像处理
 */
public class ImageBitmapFunImpl implements ImageBitmapFun {
    /**
     * @param view 需要截图的视图
     * @return
     */
    @Override
    public Bitmap imageShot(View view) {
        int viewHeight = view.getHeight();
        int viewWidth = view.getWidth();
        Bitmap tempBitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.RGB_565);
        Canvas cv = new Canvas(tempBitmap);
        cv.translate(-view.getScaleX(), -view.getScaleY());
        view.draw(cv);
        return tempBitmap;
    }

    @Override
    public Observable<Bitmap> imageShotRx(final View view) {
        return Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) {
                emitter.onNext(imageShot(view));
            }
        });
    }

    /**
     * 保存png格式
     *
     * @param bitmap
     * @return
     */
    @Override
    public File imageSave(Bitmap bitmap, String fileName, final Activity mActivity) {
        File file = null;
        FileOutputStream imageOut = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            file = new File(Environment.getExternalStorageDirectory(), HWImageManager.getInstance().getAppDirName());
            if (!file.exists()) {
                file.mkdir();
            }
            File childFile = new File(file, fileName + ".png");
            try {
                imageOut = new FileOutputStream(childFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, imageOut);
                // 发送广播，通知刷新图库的显示
                if (mActivity != null) {
                    mActivity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                }
                imageOut.flush();
                imageOut.close();
                return childFile;
            } catch (Exception e) {
                e.printStackTrace();
                if (imageOut != null) {
                    try {
                        imageOut.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                return null;
            }
        }
        return null;
    }

    @Override
    public Observable<File> imageSaveRx(final Bitmap bitmap, final String fileName, final Activity mActivity) {
        return Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(ObservableEmitter<File> emitter) {
                emitter.onNext(imageSave(bitmap, fileName, mActivity));
            }
        });
    }

    /**
     * @param bitmap
     * @param mode   压缩模式
     * @return
     */
    @Override
    public Bitmap imageCompress(Bitmap bitmap, ImageMode mode) {
        Bitmap bitmapCompress = null;
        switch (mode) {
            case COMPRESS_QUALITY:
                //图片质量压缩
                bitmapCompress = ImageBitmapCompress.compressImage(bitmap);
                break;
            case COMPRESS_SIZE:
                //图片大小按比例压缩
                bitmapCompress = ImageBitmapCompress.compressScale(bitmap);
                break;
        }
        return bitmapCompress;
    }


    /**
     * @param act
     * @param url
     * @param respCode  onActivityResult 回调
     * @param aspectX   裁剪的宽的比例
     * @param aspectY   裁剪的高的比例
     * @param outWidth  裁剪图片的宽 数值越大月清晰,但注意越高的数值的话,有可能会奔溃
     * @param outHeight 裁剪图片的高
     * @return 返回当前裁剪的图片录制{new File(Uri)}就可以直接用了
     */
    @Override
    public Uri imageCutOut(Activity act, Uri url, int respCode, int aspectX, int aspectY, int outWidth, int outHeight) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(url, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        // aspectX aspectY 是宽高的比例
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        // outputX outputY 是裁剪图片宽高
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", outWidth);
        intent.putExtra("outputY", outHeight);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(avatarFile));//这里只能用Uri.fromFile，不然部分手机会报错，裁剪后无法保存图片
        //不能用FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + “.fileProvider”, file)
        Uri parse = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + act.getPackageName() + System.currentTimeMillis() + "crop.webp");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, parse);
        act.startActivityForResult(intent, respCode);
        return parse;
    }

    /**
     * @param gravity 合并的位置
     * @param bitmaps
     */
    @Override
    public Bitmap imageMerge(int gravity, Bitmap... bitmaps) {
        Canvas canvas;
        int allHeight=0;//适用于上下合并
        int allWidth=0;//适用于左右合并
        int maxWidth=0;
        int maxHeight=0;
        for (Bitmap bitmap : bitmaps) {
            int height = bitmap.getHeight();
            int width=bitmap.getWidth();
            if(maxWidth<width){
                maxWidth=width;
            }
            if(maxHeight<height){
                maxHeight=height;
            }
            allHeight+=height;
            allWidth+=width;
        }
        Bitmap tempBitmap;
        switch (gravity){
            case Gravity.BOTTOM:
            case Gravity.TOP:
                //由上往下
                tempBitmap= Bitmap.createBitmap(maxWidth,allHeight, Bitmap.Config.ARGB_8888);
                canvas=new Canvas(tempBitmap);
                int lastHeight=0;
                for (Bitmap bitmap : bitmaps) {
                    canvas.drawBitmap(bitmap,0,lastHeight,null);
                    lastHeight=bitmap.getHeight();
                }
                canvas.save();
                canvas.restore();
                return tempBitmap;
            case Gravity.LEFT:
            case Gravity.RIGHT:
                //由左到右
                tempBitmap= Bitmap.createBitmap(allWidth,maxHeight, Bitmap.Config.ARGB_8888);
                canvas=new Canvas(tempBitmap);
                int lastWidth=0;
                for (Bitmap bitmap : bitmaps) {
                    canvas.drawBitmap(bitmap,lastWidth,0,null);
                    lastWidth=bitmap.getWidth();
                }
                canvas.save();
                canvas.restore();
                return tempBitmap;
            case Gravity.CENTER:
                //中间叠加 渐变融合
                tempBitmap=Bitmap.createBitmap(maxWidth,maxHeight,Bitmap.Config.ARGB_8888);
                canvas=new Canvas(tempBitmap);
                for (Bitmap bitmap : bitmaps) {
                    canvas.drawBitmap(bitmap,0,0,null);
                }
                canvas.save();
                canvas.restore();
                return tempBitmap;

        }
        return null;
    }

    /**
     * 相册打开,在onActivityOnResult这里回调,获取回调参数:(Uri)类型:data.data
     *
     * @param act
     * @param respCode
     */
    @Override
    public void imageOpenPhoto(Activity act, int respCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        act.startActivityForResult(intent, respCode);
    }

    /**
     * @param act
     * @param respCode
     */
    @Override
    public void imageOpenCamera(Activity act, int respCode) {

    }

    /**
     * @param bitmap
     * @param highLight 是否高亮
     */
    @Override
    public void imageHighlight(Bitmap bitmap, boolean highLight) {

    }

    /**
     * @param erString 二维码内容
     * @param mContext
     * @param width
     * @param height
     * @return
     */
    @Override
    public Bitmap imageErCode(String erString, Context mContext, int width, int height) {
        return null;
    }

    /**
     * @param erImage
     * @return
     */
    @Override
    public String imageErdecode(Bitmap erImage) {
        return null;
    }

    /**
     * @param image
     * @param scale
     * @return
     */
    @Override
    public Bitmap iamgeZoom(Bitmap image, float scale) {
        return null;
    }
}
