package com.example.mylibrary.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * created by ws
 * on 2021/1/12
 * describe:
 */
public  abstract class WsBaseFragment<M extends ICommonModelWs> extends BaseFragment implements ICommonViewWs{

    public M m;
    public ICommonPresenterWs presenterWs;
    private FragmentActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(setLayout(), container, false);
        activity = getActivity();
        return inflate;
    }

    /**初始化M层必须自己新建model,继承ICommonModelWs
     * 初始化P层根据需要自己新建或使用默认WsMvpPresenter,要使用默认就不用管initPresenter..*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        m = steModel();
        ICommonPresenterWs iCommonPresenterWs = setPresenter();
        if (iCommonPresenterWs!= null){
            presenterWs = iCommonPresenterWs;
        }else {
            presenterWs= WsMvpPresenter.getWsMvpPresenter(this, this.m);
        }
        initView();
        initData();

    }

    protected abstract ICommonPresenterWs setPresenter();

    protected abstract M steModel();

    protected abstract int setLayout();


}
