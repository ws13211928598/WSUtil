package com.example.mylibrary.common;

import com.example.mylibrary.utils.LoadView;

/**
 * created by ws
 * on 2021/1/12
 * describe:
 */
public class WsMvpPresenter implements ICommonPresenterWs{

    private  static ICommonModelWs commonModelWs;
    private  static ICommonViewWs commonViewWs;
    public static WsMvpPresenter wsMvpPresenter;
    public static LoadView loadView;
    public static int user = -1;
    static int netWorkNumber = 0;
    private  <M extends ICommonModelWs> WsMvpPresenter() {

    }
    public static WsMvpPresenter getWsMvpPresenter(ICommonViewWs viewWs, ICommonModelWs m){
        if (wsMvpPresenter==null) wsMvpPresenter = new WsMvpPresenter();
        commonViewWs = viewWs;
        commonModelWs = m;
        return wsMvpPresenter;

    }
//LoadView instance = LoadView.getInstance();  选择要不要加载图标
    public static WsMvpPresenter getWsMvpPresenter(ICommonViewWs viewWs, ICommonModelWs m, LoadView loadView1){
        if (wsMvpPresenter==null) wsMvpPresenter = new WsMvpPresenter();
        commonViewWs = viewWs;
        loadView = loadView1;
        commonModelWs = m;
        loadView.onDetachedFromWindow();
        return wsMvpPresenter;
    }

    @Override
    public void getData(int mode,int users, Object[] objects) {
        if (loadView!=null&&netWorkNumber==0){
            loadView.onAttachedToWindow();
        }
        commonModelWs.initData(mode,this,objects);
        if (user==users){
            netWorkNumber++;
        }
    }


    @Override
    public void onSuccess(int mode, int users, Object[] objects) {
        if (user==users){
            netWorkNumber--;
        }
        if (loadView!=null&&netWorkNumber<=0){
            loadView.onDetachedFromWindow();
        }
        commonViewWs.onSuccess(mode,users,objects);
    }

    @Override
    public void onFailed(int users, Throwable throwable) {
        if (user==users){
            netWorkNumber--;
        }
        if (loadView!=null&&netWorkNumber<=0){
            loadView.onDetachedFromWindow();
        }
        commonViewWs.onFailed(users,throwable);
    }
}
