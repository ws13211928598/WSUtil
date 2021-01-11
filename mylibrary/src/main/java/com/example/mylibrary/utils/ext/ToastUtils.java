package com.example.mylibrary.utils.ext;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.example.mylibrary.utils.StaticToast;


/**
 * ToastUtils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-12-9
 */
public class ToastUtils {
    public final static int ERROR = 1,RIGHT=2,PROMPTY=3,OTHER=4;
    private ToastUtils() {
        throw new AssertionError();
    }

    public static void show(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    /**
     *
     * @param context
     * @param text
     * @param status 要显示 的状态
     * @param duration 要显示的时间
     */
    public static void show(Context context, CharSequence text,int status,int duration) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    /**
     *
     * @param context
     * @param status 状态
     * @param text 文本
     */
    public static void show(Context context,int status, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence text, int duration) {
    	if(!TextUtils.isEmpty(text)){
            StaticToast.showShortToast(context,text);
    	}
    }

    public static void showInMiddle(Context context, CharSequence text) {
        if(!TextUtils.isEmpty(text)){
           StaticToast.showShortToast(context,text, Gravity.CENTER_VERTICAL);
        }
    }
}
