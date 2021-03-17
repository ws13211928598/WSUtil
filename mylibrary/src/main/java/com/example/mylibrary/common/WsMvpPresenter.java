package com.example.mylibrary.common;

import com.example.mylibrary.utils.LoadView;

/**
 * created by ws
 * on 2021/1/12
 * describe:
 */
public class WsMvpPresenter implements ICommonPresenterWs{

    private   ICommonModelWs commonModelWs;
    private   ICommonViewWs commonViewWs;
    public  LoadView loadView;
    public static  int userID = -1;
    int netWorkNumber = 0;

    public WsMvpPresenter(ICommonViewWs commonViewWs,ICommonModelWs commonModelWs ) {
        this.commonModelWs = commonModelWs;
        this.commonViewWs = commonViewWs;
    }

    public void setLoadView(LoadView loadView) {
        this.loadView = loadView;
    }

    @Override
    public void getData(int mode,int users, Object[] objects) {
        if (loadView!=null&&netWorkNumber>0){
            loadView.show();
        }
        commonModelWs.initData(mode,users,this,objects);
        if (users==userID) {
            netWorkNumber++;
        }
    }


    @Override
    public void onSuccess(int mode, int users, Object[] objects) {
        if (users==userID) {
            netWorkNumber--;
        }

        if (loadView!=null&&netWorkNumber<=0){
            loadView.dismiss();
        }
        commonViewWs.onSuccess(mode,users,objects);
    }

    @Override
    public void onFailed(int users, Throwable throwable) {
        if (users==userID) {
            netWorkNumber--;
        }
        if (loadView!=null&&netWorkNumber<=0){
            loadView.dismiss();
        }
        commonViewWs.onFailed(users,throwable);
    }
}
