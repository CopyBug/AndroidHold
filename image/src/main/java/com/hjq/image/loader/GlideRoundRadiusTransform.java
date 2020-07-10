package com.hjq.image.loader;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.bumptech.glide.util.Util;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class GlideRoundRadiusTransform extends BitmapTransformation {

    //  此处用实际类的完整路径
    private static final String ID = "com.xxx.xxx.xxxx.xxx.GlideRoundRadiusTransform";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private final int roundingRadius;

    private boolean isLeftTop=true, isRightTop=true, isLeftBottom=true, isRightBottom=true;

    private static GlideRoundRadiusTransform mInstance;
    public GlideRoundRadiusTransform(int roundingRadius) {
        this.roundingRadius = roundingRadius;
    }

    /**
     * 需要设置圆角的部分
     *
     * @param leftTop     左上角
     * @param rightTop    右上角
     * @param leftBottom  左下角
     * @param rightBottom 右下角
     */
    public void setNeedCorner(boolean leftTop, boolean rightTop, boolean leftBottom, boolean rightBottom) {
        isLeftTop = leftTop;
        isRightTop = rightTop;
        isLeftBottom = leftBottom;
        isRightBottom = rightBottom;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.roundedCorners(pool, toTransform,
                isLeftTop ? roundingRadius : 0,
                isRightTop ? roundingRadius : 0,
                isRightBottom ? roundingRadius : 0,
                isLeftBottom ? roundingRadius : 0);
    }

    public static GlideRoundRadiusTransform getDefault() {
        if (mInstance == null) {
            synchronized (GlideRoundRadiusTransform.class) {
                if (mInstance == null) {
                    mInstance = new GlideRoundRadiusTransform(10);
                    mInstance.setNeedCorner(true, false, true, false);
                }
            }
        }
        return mInstance;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GlideRoundRadiusTransform) {
            GlideRoundRadiusTransform other = (GlideRoundRadiusTransform) o;
            return roundingRadius == other.roundingRadius;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Util.hashCode(ID.hashCode(), Util.hashCode(roundingRadius));
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);

        byte[] radiusData = ByteBuffer.allocate(4).putInt(roundingRadius).array();
        messageDigest.update(radiusData);
    }
}
