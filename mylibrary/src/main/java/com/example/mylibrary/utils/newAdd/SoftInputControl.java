package com.example.mylibrary.utils.newAdd;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by 任小龙 on 2020/2/26.
 */
public class SoftInputControl {
    public static void defaultHideInput(Activity pContext){
        pContext.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public static void hideSoftInput(Activity pActivity, View view){
        InputMethodManager inputMethodManager = (InputMethodManager) (pActivity.getSystemService(Context.INPUT_METHOD_SERVICE));
        inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
    }
}
