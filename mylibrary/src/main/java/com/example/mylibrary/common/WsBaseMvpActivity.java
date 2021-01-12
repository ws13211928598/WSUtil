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
    M m;
    private ICommonPresenterWs commonPresenterWs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        m = initModel();
        ICommonPresenterWs presenterWs= initPresenter();
        if (presenterWs != null ){
            commonPresenterWs = presenterWs;
        }else {
            commonPresenterWs = new WsMvpPresenter(this, this.m);
        }
        initView();
        initData();

    }

    protected abstract ICommonPresenterWs initPresenter();


    @Override
    public void initSmartLoad(SmartRefreshLayout smartRefreshLayout, int mode, Object[] objects) {
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                onSuccess(mode,objects);
                smartRefreshLayout.finishLoadMore(2000);

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                onSuccess(mode,objects);
                smartRefreshLayout.finishRefresh(2000);
            }
            });
    }

    protected abstract M initModel();
    protected abstract int initLayout();
}

