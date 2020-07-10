package com.example.framework.imageFun;

import android.widget.ImageView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.databinding.BindingAdapter;

import com.hjq.image.ImageLoader;
import com.hjq.image.LoadingImageView;

/**
 * Time       : 2020/07/06 下午2:39
 * Author     : sion
 * Description: 适用于databing的视图绑定的图片加载库
 */

public class ImageLoadingFun {

    /**
     * 网络加载
     *
     * @param image
     * @param url
     */
    @BindingAdapter("android:glideSrc")
    public static void glideSrc(ImageView image, String url) {
        if (image != null) {
            ImageLoader.with(image.getContext())
                    .load(url)
                    .into(image);
        }
    }

    /**
     * 本地图片资源加载
     *
     * @param image
     * @param url
     */
    @BindingAdapter("android:glideSrc")
    public static void glideSrc(ImageView image, @DrawableRes int url) {
        if (image != null) {
            ImageLoader.with(image.getContext())
                    .load(url)
                    .into(image);
        }
    }

    /**
     * 圆形图片加载
     *
     * @param image
     * @param url
     */
    @BindingAdapter("android:glideRoundSrc")
    public static void glideRoundSrc(ImageView image, String url) {
        if (image != null) {
            ImageLoader.with(image.getContext())
                    .load(url)
                    .circle()
                    .into(image);
        }
    }

    /**
     * 带有边框的圆形图片
     *
     * @param image
     * @param url   链接
     * @param color 颜色
     */
    @BindingAdapter(value = {"android:glideRoundColorSrc",  "android:glideRoundColor"})
    public static void glideRoundColorSrc(ImageView image, String url,@ColorRes int color) {
        if (image != null) {
            ImageLoader.with(image.getContext())
                    .load(url)
                    .glideCircleWithBorder(2, color)
                    .into(image);
        }
    }
    /**
     * 带有边框的圆形图片
     *
     * @param image
     * @param url   链接
     * @param radio 圆角
     * @param color 颜色
     */
    @BindingAdapter(value = {"android:glideRoundColorSrc", "android:glideRoundColorRadio", "android:glideRoundColor"})
    public static void glideRoundColorSrc(ImageView image, String url, int radio, @ColorRes int color) {
        if (image != null) {
            ImageLoader.with(image.getContext())
                    .load(url)
                    .glideCircleWithBorder(radio, color)
                    .into(image);
        }
    }

    /**
     * 矩形圆角
     *
     * @param image
     * @param url   链接
     * @param radio 圆角
     */
    @BindingAdapter(value = {"android:glideRecRoundSrc", "android:glideRecRoundRadio"})
    public static void glideRecRoundSrc(ImageView image, String url, int radio) {
        if (image != null) {
            ImageLoader.with(image.getContext())
                    .load(url)
                    .circle(radio)
                    .into(image);
        }
    }

    /**
     * 指定图片大小
     *
     * @param image
     * @param url
     * @param width  宽度
     * @param height 高度
     */
    @BindingAdapter(value = {"android:glideSizeSrc", "android:glideSizeWidth", "android:glideSizeHeight"})
    public static void glideSizeSrc(ImageView image, String url, int width, int height) {
        if (image != null) {
            ImageLoader.with(image.getContext())
                    .load(url)
                    .override(width, height)
                    .into(image);
        }
    }

    /**
     * 指定图片大小
     *
     * @param image
     * @param url
     * @param width  宽度
     * @param height 高度
     */
    @BindingAdapter(value = {"android:glideSizeSrc", "android:glideSizeWidth", "android:glideSizeHeight"})
    public static void glideSizeSrc(ImageView image, int url, int width, int height) {
        if (image != null) {
            ImageLoader.with(image.getContext())
                    .load(url)
                    .override(width, height)
                    .into(image);
        }
    }


    /**
     * 指定图片大小,并且带有圆角
     *
     * @param image
     * @param url
     * @param radio
     * @param width
     * @param height
     */
    @BindingAdapter(value = {"android:glideSizeRadioSrc",  "android:glideSizeRadio", "android:glideSizeRadioWidth", "android:glideSizeRadioHeight"})
    public static void glideSizeRadioSrc(ImageView image, String url, int radio, int width, int height) {
        if (image != null) {
            ImageLoader.with(image.getContext())
                    .load(url)
                    .override(width, height)
                    .circle(radio)
                    .into(image);
        }
    }

    /**
     * 毛玻璃
     * @param image
     * @param url
     * @param radius 圆角
     * @param sampling
     */
    @BindingAdapter(value = {"android:glideBlurTransSrc", "android:glideBlurTransRadius", "android:glideBlurTransSampling"})
    public static void glideBlurTransSrc(ImageView image, String url, int radius, int sampling) {
        if (image != null) {
            ImageLoader.with(image.getContext())
                    .load(url)
                    .blurTransformation(radius, sampling)
                    .into(image);
        }
    }

    @BindingAdapter(value = {"android:glideRoundRadiusSrc", "android:glideRoundRadiusTop", "android:glideRoundRadiusBottom","android:glideRoundRadius"})
    public static void glideRoundRadiusSrc(ImageView image,String url,boolean isTop,boolean isBottom,int radius){
        if(image!=null){
            ImageLoader.with(image.getContext())
                    .load(url)
                    .roundRadiusTransform(isTop,isTop,isBottom,isBottom,radius)
                    .into(image);
        }
    }
}
