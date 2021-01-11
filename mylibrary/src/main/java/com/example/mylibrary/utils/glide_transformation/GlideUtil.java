package com.example.mylibrary.utils.glide_transformation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.mylibrary.R;
import com.example.mylibrary.utils.UtilsApplication;



/**
 * Created by 任小龙 on 2019/12/12.
 */
public class GlideUtil {
    public static <P> void loadImage(ImageView imageView, P filePath) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .dontAnimate();
        Glide.with(UtilsApplication.getUtilsApplicationContext())
                .load(filePath)
                .apply(options)
                .into(imageView);
    }

    public static void loadImageWithSize(Context context, int resizeX, int resizeY, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .override(resizeX, resizeY))
                .into(imageView);
    }

    /**
     * 通过glide动态修改view的形状
     * 注意：如果是recycleview 的item中使用圆角图形，尽量在布局中使用自定义圆角图片，因为在刷新时，重绘会影响
     * @param imageView
     * @param filePath
     * @param listener
     * @param radius
     */
    public static void loadCornerImage(ImageView imageView, String filePath, RequestListener listener, float radius) {
        CornerTransform transform = new CornerTransform(UtilsApplication.getUtilsApplicationContext(), radius);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .dontAnimate()
                .transform(transform);
        Glide.with(UtilsApplication.getUtilsApplicationContext())
                .load(filePath)
                .apply(options)
                .listener(listener)
                .into(imageView);
    }

    public static void loadRoundImage(ImageView pImageView, String filePath) {
        Glide.with(UtilsApplication.getUtilsApplicationContext()).load(filePath)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(pImageView);
    }

    /**
     * 设置背景模糊，失败时使用默认图片
     */
    public static void loadBlurredBackground(String imagePath, final ImageView imageView) {
        Glide.with(UtilsApplication.getUtilsApplicationContext()).load(imagePath)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(20, 3)))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(UtilsApplication.getUtilsApplicationContext()).load(R.drawable.ic_pic_load)
                                        .apply(RequestOptions.bitmapTransform(new BlurTransformation(20, 3)))
                                        .into(imageView);
                            }
                        });
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }
}
