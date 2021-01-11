package com.example.utils.newAdd;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;


/**
 * @author Administrator
 */

public class DensityUtil {

    /**
     * 获取导航栏高度
     *
     * @param context
     * @return
     */
    public static int getNavigationHeight(Context context) {
        int result = 0;
        int resourceId = 0;
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid != 0) {
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        } else
            return 0;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获得屏幕的尺寸
     *
     * @param context 上下文
     * @return 屏幕尺寸Point
     */
    public static Point getDisplaySize(Context context) {
        Point point = new Point();
        //WindowManager windowManager = ((Activity) context).getWindowManager();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        display.getSize(point);
        return point;
    }

    /**
     * 将dp值转换为px值
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return 转换后的px值
     */
    public static int dip2px(Context context, float dpValue) {
        if (context !=null){
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }else{
            return  0;
        }

    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context 上下文
     * @param spValue Sp值
     * @return 转换后的Px值
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 将px值转换为dp值
     *
     * @param context 上下文
     * @param pxValue px值
     * @return 转换后的dp值
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
