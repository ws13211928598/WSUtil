package com.example.mylibrary.common;

/**
 * created by ws
 * on 2021/1/12
 * describe:
 */
public class WsMvpPresenter implements ICommonPresenterWs{

    private final ICommonModelWs commonModelWs;
    private final ICommonViewWs commonViewWs;

    public <M extends ICommonModelWs> WsMvpPresenter(ICommonViewWs viewWs, ICommonModelWs m) {
        commonViewWs = viewWs;
        commonModelWs = m;

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
