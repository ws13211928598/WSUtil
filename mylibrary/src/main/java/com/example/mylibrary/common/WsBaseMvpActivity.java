package com.example.mylibrary.common;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * created by ws
 * on 2021/1/12
 * describe:
 */
public abstract class WsBaseMvpActivity<M extends ICommonModelWs> extends BaseActivity implements ICommonViewWs {
    public M m;
    public ICommonPresenterWs commonPresenterWs;
    boolean PresenterB = false;
    public int user;

    /**初始化M层必须自己新建model,继承ICommonModelWs
     * 初始化P层根据需要自己新建或使用默认WsMvpPresenter,要使用默认就不用管initPresenter*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int i = initLayout();
        user = i;
        setContentView(i);
        m = initModel();
        ICommonPresenterWs presenterWs= initPresenter();
        if (presenterWs != null ){
            commonPresenterWs = presenterWs;
        }else {
            PresenterB = true;
            commonPresenterWs = WsMvpPresenter.getWsMvpPresenter(this, this.m);
            WsMvpPresenter.user = user;
        }
        initView();
        initData();

    }

    protected abstract ICommonPresenterWs initPresenter();



    protected abstract M initModel();
    protected abstract int initLayout();

    @Override
    protected void onPause() {
        super.onPause();
        if (PresenterB){
            WsMvpPresenter.user = -1;
            WsMvpPresenter.netWorkNumber = 0;
        }
    }
}

