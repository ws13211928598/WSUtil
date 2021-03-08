package com.example.mylibrary.common;

import com.example.mylibrary.ws.SuperInterceptor;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.OkHttpClient;
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
    public   Retrofit initRetrofit(String baseUrl) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new SuperInterceptor()).build())
                .build();

        return build;
    }


    /*public static OkHttpClient initClient = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .addInterceptor(new CommonHeaderInterceptor())
            .addInterceptor(new LogInterceptor())
            .proxySelector(new ProxySelector())
            .build();*/
    //添加拦截器版本的
    public   Retrofit initRetrofit(String baseUrl,OkHttpClient okHttpClient) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .client(new OkHttpClient.Builder().addInterceptor(new SuperInterceptor()).build())
                .build();

        return build;
    }

    //手动添加任意数量的拦截器,添加完后需要.build
    public   Retrofit.Builder addMoreInterceptor(String baseUrl) {
        Retrofit.Builder client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        return client;
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
