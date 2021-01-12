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
    public static Retrofit retrofit;
    public  String url ;

    private WsNetManager(){}

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

    private  Retrofit initRetrofit() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return build;
    }

    public <W> void newtWork(Flowable<W> info,ICommonPresenterWs iCommonPresenterWs,int mode){
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
