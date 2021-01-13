package com.example.mylibrary.common;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * created by ws
 * on 2021/1/12
 * describe:
 */
public class WsNetManager {
    private static WsNetManager wsNetManager;
    public  Retrofit retrofit;
    public  String url ;

    private WsNetManager(){}

    /**
     *通过单例模式获得对象*/
    public static WsNetManager getInstance(){
        if(wsNetManager == null){
            synchronized (WsNetManager.class){
                if (wsNetManager == null){
                    wsNetManager = new WsNetManager();
                }
            }
        }
        return wsNetManager;
    }

    /**获取Retrofit对象,后需要手动.create和.getXxx方法,得到Flowable对象,调用netWork方法并传入*/
    private  Retrofit initRetrofit() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return build;
    }

    /**传入Flowable对象,模式,数据*/
    public <W> void newtWork(Flowable<W> info,ICommonPresenterWs iCommonPresenterWs,int mode,Object[] objects){
        ResourceSubscriber<W> resourceSubscriber = info.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<W>() {
                    @Override
                    public void onNext(W w) {
                        iCommonPresenterWs.onSuccess(mode, w);
                    }

                    @Override
                    public void onError(Throwable t) {
                        iCommonPresenterWs.onFailed(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
