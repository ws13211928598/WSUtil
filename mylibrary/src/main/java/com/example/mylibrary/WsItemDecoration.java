package com.example.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * created by ws
 * on 2021/1/9
 * describe:
 */
public class WsItemDecoration extends RecyclerView.ItemDecoration {
    float dividerHeight = 1;
    int mode = 0;
    boolean fistHorizontal;

    Context context;
    Paint paintVertical = new Paint();

    int colorVertical = Color.BLACK;
    int widthVertical = 1;

    Paint paintHorizontal = new Paint();
    int colorHorizontal = Color.BLACK;
    int widthHorizontal = 1;

    public WsItemDecoration(Context context) {
        this.context = context;
    }

    public WsItemDecoration setPaintHorizontal(int widthHorizontal, int color){
        this.widthHorizontal = widthHorizontal;
        colorHorizontal = context.getResources().getColor(color);
        paintHorizontal.setColor(colorHorizontal);
        return this;
    }

    public WsItemDecoration setPaintVertical(int widthVertical,int color){
        this.widthVertical = widthVertical;
        colorVertical = context.getResources().getColor(color);
        paintVertical.setColor(colorVertical);
        return this;
    }

    public WsItemDecoration setFIstHorizontal(boolean fistHorizontal){
        this.fistHorizontal = fistHorizontal;
        return this;
    }

    //设置模式,默认为0
    public WsItemDecoration setMode(int f0_2){
        this.mode = f0_2;
        return this;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        switch (mode){
            case 0:
                outRect.top = widthHorizontal;
                break;
            case 1:
                outRect.left = widthVertical;
                outRect.right = widthVertical;
                break;
            case 2:
                outRect.top = widthHorizontal;
                outRect.left = widthVertical;
                outRect.right = widthVertical;
                break;
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        switch (mode){
            case 0:
                drawHorizontal(c,parent);
                break;
            case 1:
                drawVertical(c,parent);

                break;
            case 2:
                drawVerticalAndHorizontal(c,parent);
                break;
        }
    }

    private void drawVerticalAndHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            if (fistHorizontal){
                int top = childAt.getTop()-widthVertical;
                int bottom = childAt.getBottom();
                int left = childAt.getLeft()-widthHorizontal;
                int right = childAt.getRight()+widthHorizontal;
                c.drawRect(left,top,right,bottom,paintVertical);

                int topH = childAt.getTop()-widthHorizontal;
                int bottomH = childAt.getTop();
                int leftH = childAt.getLeft()-widthHorizontal;
                int rightH = childAt.getRight()+widthHorizontal;
                c.drawRect(leftH,topH,rightH,bottomH,paintHorizontal);

            }else {

                int topH = childAt.getTop()-widthHorizontal;
                int bottomH = childAt.getTop();
                int leftH = childAt.getLeft()-widthHorizontal;
                int rightH = childAt.getRight()+widthHorizontal;
                c.drawRect(leftH,topH,rightH,bottomH,paintHorizontal);

                int topV = childAt.getTop();
                int bottomV = childAt.getBottom();
                int leftV = childAt.getLeft()-widthHorizontal;
                int rightV = childAt.getRight()+widthHorizontal;
                c.drawRect(leftV,topV,rightV,bottomV,paintVertical);

            }
        }

    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            int top = childAt.getTop()-widthHorizontal;
            int bottom = childAt.getTop();
            int left = childAt.getLeft();
            int right = childAt.getRight();
            c.drawRect(left,top,right,bottom,paintHorizontal);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            int top = childAt.getTop();
            int bottom = childAt.getBottom();
            int left = childAt.getLeft()-widthHorizontal;
            int right = childAt.getRight()+widthHorizontal;
            c.drawRect(left,top,right,bottom,paintVertical);
        }
    }
}
