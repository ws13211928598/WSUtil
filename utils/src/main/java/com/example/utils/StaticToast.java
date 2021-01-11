package com.example.utils;

/**
 * Created by zhangjia on 2016/9/7.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class StaticToast{
    private static Toast toast;
    @SuppressLint("ShowToast")
    private static void getToast(Context context) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
    }

    public static void showShortToast(Context context, CharSequence msg) {
        if(context==null){
            return;
        }
        showToast(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
    }

    public static void showShortToast(Context context, CharSequence msg, int gravity) {
        if(context==null){
            return;
        }
        showToast(context.getApplicationContext(), msg, Toast.LENGTH_SHORT, gravity);
    }

    private static void showToast(Context context, CharSequence msg,
                                  int duration) {
        try {
            if(TextUtils.isEmpty(msg) || msg.equals("null")){
                return;
            }
            getToast(context);
            toast.setText(msg);
            toast.setDuration(duration);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showToast(Context context, CharSequence msg,
                                  int duration, int gravity) {
        try {
            if(TextUtils.isEmpty(msg) || msg.equals("null")){
                return;
            }
            getToast(context);
            toast.setText(msg);
            toast.setDuration(duration);
            toast.show();
            toast.setGravity(gravity, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
