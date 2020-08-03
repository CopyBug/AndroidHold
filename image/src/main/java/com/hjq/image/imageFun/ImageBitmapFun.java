package com.hjq.image.imageFun;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;

import java.io.File;

import io.reactivex.Observable;

/**
 * Time       : 2020/07/07 上午10:42
 * Author     : sion
 * Description:
 */
public interface ImageBitmapFun {


    /**
     * 截图
     *
     * @param view 需要截图的视图
     * @return
     */
    Bitmap imageShot(View view);

    Observable<Bitmap> imageShotRx(View view);
    /**
     * 图片保存
     *
     * @param bitmap
     * @return
     */
    File imageSave(Bitmap bitmap, String fileName, Activity mActivity);
    Observable<File> imageSaveRx(Bitmap bitmap, String fileName, Activity mActivity);

    /**
     * 图片压缩
     *
     * @param bitmap
     * @param mode   压缩模式
     * @return
     */
    Bitmap imageCompress(Bitmap bitmap, ImageMode mode);

    /**
     * 图片裁剪
     * @param act
     * @param url
     * @param respCode onActivityResult 回调
     */
    Uri imageCutOut(Activity act, Uri url, int respCode, int aspectX, int aspectY, int outWidth, int outHeight);

    /**
     * @param gravity  合并的位置
     * @param bitmaps
     */
    Bitmap imageMerge(int gravity, Bitmap... bitmaps);

    /**
     * 打开相册并且返回数据
     * @param act
     * @param respCode
     */
    void imageOpenPhoto(Activity act, int respCode);

    /**
     * 打开相机并且返回数据
     * @param act
     * @param respCode
     */
    File imageOpenCamera(Activity act, int respCode);

    /**
     * 图像的高亮
     * @param bitmap
     * @param highLight 是否高亮
     */
    void imageHighlight(Bitmap bitmap, boolean highLight);

    /**
     * 文字生成二维码
     * @param erString 二维码内容
     * @param mContext
     * @param width
     * @param height
     * @return
     */
    Bitmap imageErCode(String erString, Context mContext, int width, int height);

    /**
     * 二维码解码
     * @param erImage
     * @return
     */
    String imageErdecode(Bitmap erImage);

    /**
     * 按比例缩放
     * @param image
     * @param scale
     * @return
     */
    Bitmap iamgeZoom(Bitmap image, float scale);

}
