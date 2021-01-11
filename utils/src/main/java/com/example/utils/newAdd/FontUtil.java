package com.example.utils.newAdd;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by 任小龙 on 2020/2/13.
 */
public class FontUtil {
    /**
     * 修改局部字体样式
     * @param root 要修改 view或者viewGroup
     * @param fontPath 要改成的字体样式的路劲
     */
    public static void replaceFont(View root, String fontPath) {
        if (root == null || TextUtils.isEmpty(fontPath)) {
            return;
        }
        if (root instanceof TextView) {
            TextView textView = (TextView) root;
            int style = Typeface.NORMAL;
            if (textView.getTypeface() != null) {
                style = textView.getTypeface().getStyle();
            }
            Typeface typeface = Typeface.createFromAsset(root.getContext().getAssets(),fontPath);
            textView.setTypeface(typeface, style);
        } else if (root instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) root;
            for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                replaceFont(viewGroup.getChildAt(i), fontPath);
            }
        }
    }

    /**
     * 替换单个activity的字体
     * @param context
     * @param fontPath
     */
    public static void replaceAllActivityFont(Activity context, String fontPath) {
        replaceFont(context.findViewById(android.R.id.content), fontPath);
    }

    /**
     * 全局替换字体，项目style加<item name="android:typeface">monospace</item>
     * @param pContext
     * @param fontPath
     */
    public static void initTypeface(Context pContext,String fontPath){
        Typeface typeface = Typeface.createFromAsset(pContext.getAssets(), fontPath);
        try {
            Field field = Typeface.class.getDeclaredField("MONOSPACE");
            field.setAccessible(true);
            field.set("null",typeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
