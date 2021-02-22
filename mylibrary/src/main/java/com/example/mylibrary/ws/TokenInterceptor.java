package com.example.mylibrary.ws;

import android.text.TextUtils;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class TokenInterceptor implements Interceptor {
    //为请求添加token
    String TOKEN ;
    @Override
    public Response intercept( Interceptor.Chain pChain) throws IOException {
        Request request = pChain.request();
        if (!TextUtils.isEmpty(TOKEN)){
            request = request.newBuilder().addHeader("Authorization", "Bearer" + TOKEN).build();
        }
        return pChain.proceed(request);
    }
}
