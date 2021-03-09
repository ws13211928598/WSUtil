package com.example.mylibrary.common;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * created by ws
 * on 2021/1/12
 * describe:
 */
public interface ICommonViewWs<W> {
    void onSuccess(int mode,int users,W...ws);
    void onFailed(int users,Throwable throwable);

}
