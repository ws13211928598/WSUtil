package com.example.mylibrary.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylibrary.R;

/**
 * created by ws
 * on 2021/3/9
 * describe:
 */
public class LoadView extends Dialog {
    private ImageView mProgressView;

    private TextView mTvContent;

    private String mContent;

    public static LoadView sLoadView;

    static int ContentView;
    static int imageView;
    static int TextView;
//上下文,文字内容,layout,image,text
    public static LoadView getInstance(Context pContext, String pContent,int ContentView1,int imageView1,int TextView1){
        ContentView = ContentView1;
        imageView = imageView1;
        TextView = TextView1;
        return new LoadView(pContext,pContent);
    }

    private LoadView(Context context, String content) {
        super(context, R.style.DialogStyle);
        mContent = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ContentView);
        mProgressView = findViewById(imageView);
        mTvContent = findViewById(TextView);
        mTvContent.setText(TextUtils.isEmpty(mContent) ? "加载中" : mContent);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mProgressView != null)
            mProgressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mProgressView != null){
            mProgressView.setVisibility(View.GONE);
        }
    }
}
