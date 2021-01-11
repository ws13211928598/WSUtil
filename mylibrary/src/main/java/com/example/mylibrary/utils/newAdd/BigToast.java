package com.example.mylibrary.utils.newAdd;


import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.R;

import java.lang.ref.WeakReference;


public class BigToast {

    private BigToast() {
    }

    private static WeakReference<Toast> sCache = null;
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static final long MAIN_THREAD_ID = Looper.getMainLooper().getThread().getId();
    private static int padding = 40;

    public static void show(Context context, int resId) {
        String str = context.getString(resId);
        show(context, str);
    }

    /**
     * 显示指定的字符串，若当前有正在显示的字符串Toast，直接替换文字，显示时间为 {@link Toast#LENGTH_SHORT}
     *
     * @param context
     * @param cs
     */
    public static void show(Context context, CharSequence cs) {
        show(context, cs, Toast.LENGTH_SHORT, false);
    }

    /**
     * 收起正在显示的toast
     */
    public static void dismiss() {
        Toast curToast;
        if (sCache != null && (curToast = sCache.get()) != null) {
            curToast.cancel();
            sCache = null;
        }
    }

    public static void show(final Context context, final CharSequence string, final boolean newToast) {
        if (Thread.currentThread().getId() == MAIN_THREAD_ID) {
            runInMainThread(context, string, Toast.LENGTH_SHORT, newToast);
        } else {
            sHandler.post(() -> runInMainThread(context, string, Toast.LENGTH_SHORT, newToast));
        }
    }

    private static void show(final Context context, final CharSequence string, final int duration, final boolean newToast) {
        if (Thread.currentThread().getId() == MAIN_THREAD_ID) {
            runInMainThread(context, string, duration, newToast);
        } else {
            sHandler.post(() -> runInMainThread(context, string, duration, newToast));
        }
    }

    private static void runInMainThread(Context context, CharSequence string, int duration, boolean newToast) {
        Toast oldToast;
        if (sCache != null && (oldToast = sCache.get()) != null) {
            if (newToast) {
                oldToast.cancel();
            } else {
                ((TextView) oldToast.getView()).setText(string);
                oldToast.setDuration(duration);
                oldToast.show();
                return;
            }
        }

        TextView textView1 = new TextView(context);
        textView1.setBackgroundResource(R.drawable.default_toast_bg);
        textView1.setPadding(padding,padding,padding,padding);
        textView1.setGravity(Gravity.CENTER);
        textView1.setTextColor(Color.WHITE);
        textView1.setText(string);
        textView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16f);
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(textView1);
        toast.setGravity(Gravity.CENTER, 0, 0);
        sCache = new WeakReference<>(toast);
        toast.show();
    }
}
