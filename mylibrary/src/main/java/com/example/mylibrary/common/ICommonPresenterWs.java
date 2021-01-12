package com.example.mylibrary.common;

/**
 * created by ws
 * on 2021/1/12
 * describe:
 */
public interface ICommonPresenterWs<W>extends ICommonViewWs {
    void getData(int mode,W...ws);
}
