package com.example.mylibrary.common;

/**
 * created by ws
 * on 2021/1/12
 * describe:
 */
public interface ICommonModelWs <W>{
    void initData(int mode,int user,ICommonPresenterWs iCommonPresenterWs,W...ws);
}
