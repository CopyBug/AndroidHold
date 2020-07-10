package com.hjq.image;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.hjq.copy.R;
import com.hjq.image.loader.GlideCircleWithBorder;
import com.hjq.image.loader.GlideRoundRadiusTransform;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/12/26
 * desc   : Glide 加载策略
 */
final class GlideStrategy implements ImageStrategy {

    @SuppressLint("CheckResult")
    @Override
    public void load(final ImageLoader loader) {
        RequestManager manager = getRequestManager(loader.context);

        if (loader.isGif) {
            manager.asGif();
        }
        if (loader.isBitMap) {
            manager.asBitmap();
        }

        final ImageView.ScaleType scaleType = loader.view.getScaleType();
        final RequestBuilder<Bitmap> builder;
        if (loader.url != null && !"".equals(loader.url)) {
            builder = manager.asBitmap().load(loader.url.trim());

        } else if (loader.resourceId != 0) {

            builder = manager.asBitmap().load(loader.resourceId);
        }else if(loader.fileUri!=null){
            builder = manager.asBitmap().load(loader.fileUri);
        }else  if(loader.bitmap!=null){
            builder=manager.asBitmap().load(loader.bitmap);
        }
        else {
            builder = manager.asBitmap().load(loader.error);
        }
        if (loader.loading) {
            builder.placeholder(R.drawable.anim_loading);
            loader.view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }


        if (loader.placeholder != null) {
            final RequestOptions options = RequestOptions.errorOf(loader.error);
            if (loader.circle != 0) {
                if (loader.circle == Integer.MAX_VALUE) {
                    // 裁剪成圆形
                    options.circleCrop();
                } else {
                    // 圆角裁剪
                    options.transform(new RoundedCorners(loader.circle));
                }
            }

            builder.apply(options);
        }

        if (loader.width != 0 && loader.height != 0) {
            builder.override(loader.width, loader.height);
        }
        if (loader.borderWidth != 0 && loader.borderColor != 0) {
            final RequestOptions options = RequestOptions.errorOf(loader.error).placeholder(R.drawable.anim_loading);
            options.transform(new GlideCircleWithBorder((Context) loader.context, loader.borderWidth, loader.borderColor));
            builder.apply(options);
        }
        if (loader.radius != 0 && loader.sampling != 0) {
            final RequestOptions options = RequestOptions.errorOf(loader.error).placeholder(R.drawable.anim_loading);
            options.transform(new BlurTransformation(loader.radius, loader.sampling));
            builder.apply(options);
        }
        if (loader.differentAngle) {
            final RequestOptions options = RequestOptions.errorOf(loader.error).placeholder(R.drawable.anim_loading);
            GlideRoundRadiusTransform glideRoundRadiusTransform = new GlideRoundRadiusTransform(loader.roundingRadius);
            glideRoundRadiusTransform.setNeedCorner(loader.isLeftTop, loader.isRightTop, loader.isLeftBottom, loader.isRightBottom);
            options.transform(glideRoundRadiusTransform);
            builder.apply(options);
        }
        builder.listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                loader.view.setScaleType(scaleType);
                return false;
            }

        });
        if (loader.view instanceof LoadingImageView) {
            builder.into(loader.view);
//            builder.into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                    loader.view.setImageBitmap(resource);
//                }
//
//                @Override
//                public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                    super.onLoadFailed(errorDrawable);
//                }
//
//                @Override
//                public void onLoadStarted(@Nullable Drawable placeholder) {
////                    super.onLoadStarted(placeholder);
//                }
//            });
        } else {
            builder.into(loader.view);
        }

    }

    /**
     * 获取一个 Glide 的请求对象
     */
    private RequestManager getRequestManager(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        } else if (object instanceof Context) {
            if (object instanceof FragmentActivity) {
                return Glide.with((FragmentActivity) object);
            } else if (object instanceof Activity) {
                return Glide.with((Activity) object);
            } else {
                return Glide.with((Context) object);
            }
        } else if (object instanceof Fragment) {
            return Glide.with((Fragment) object);
        } else if (object instanceof androidx.fragment.app.Fragment) {
            return Glide.with((androidx.fragment.app.Fragment) object);
        }
        // 如果不是上面这几种类型就直接抛出异常
        throw new IllegalArgumentException("This object is illegal");
    }
}