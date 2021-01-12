package com.example.mylibrary.common;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * created by ws
 * on 2021/1/12
 * describe:
 */
public interface ICommonViewWs<W> {
    void onSuccess(int mode,W...ws);
    void onFailed(Throwable throwable);

    default void initSmartLoad(SmartRefreshLayout smartRefreshLayout,int mode,W...ws) {

    }
}
