package com.example.mylibrary.common;

/**
 * created by ws
 * on 2021/1/12
 * describe:
 */
public class WsMvpPresenter implements ICommonPresenterWs{

    private  static ICommonModelWs commonModelWs;
    private  static ICommonViewWs commonViewWs;
    public static WsMvpPresenter wsMvpPresenter;
    private  <M extends ICommonModelWs> WsMvpPresenter() {

    }
    public static WsMvpPresenter getWsMvpPresenter(ICommonViewWs viewWs, ICommonModelWs m){
        if (wsMvpPresenter==null) wsMvpPresenter = new WsMvpPresenter();
        commonViewWs = viewWs;
        commonModelWs = m;
        return wsMvpPresenter;

    }

    @Override
    public void getData(int mode, Object[] objects) {
        commonModelWs.initData(mode,this,objects);
    }

    @Override
    public void onSuccess(int mode, Object[] objects) {
        commonViewWs.onSuccess(mode,objects);
    }

    @Override
    public void onFailed(Throwable throwable) {
        commonViewWs.onFailed(throwable);
    }
}
