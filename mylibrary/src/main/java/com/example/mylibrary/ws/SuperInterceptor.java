package com.example.mylibrary.ws;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * created by ws
 * on
 * describe:
 */
public class SuperInterceptor implements Interceptor {
    private static final String TAG = "asd";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        String string = proceed.peekBody(Long.MAX_VALUE).string();
        Log.d(TAG, "intercept: "+string);

        return proceed;
    }
}
