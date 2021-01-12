package com.example.mylibrary.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public  abstract class WsBaseFragment<M extends ICommonModelWs> extends BaseFragment implements ICommonViewWs{

    private M m;
    private ICommonPresenterWs presenterWs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(setLayout(), container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        m = steModel();
        ICommonPresenterWs iCommonPresenterWs = setPresenter();
        if (iCommonPresenterWs!= null){
            presenterWs = iCommonPresenterWs;
        }else {
            presenterWs= new WsMvpPresenter(this,m);
        }
        initView();
        initData();

    }

    protected abstract ICommonPresenterWs setPresenter();

    protected abstract M steModel();

    protected abstract int setLayout();

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
}
