package com.example.mylibrary;

import android.util.Log;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * created by ws
 * on 2020/12/30
 * describe:
 */
public class WsUpLoading {
    private static final String TAG = "asd";
    /**Gets MultiPartBody.part. Provides the file save path
     *              /storage/emulated/0/xxx.jpg(xxx.apk)
     *             /storage/emulated/0/mypicter/y42.jpg
     *             /storage/emulated/0/Pictures/y28.jpg
     *             */
    public MultipartBody.Part getMultiPart(String filePath){
        MediaType parse = MediaType.parse("application/octet-stream");
        File file = new File(filePath);

        /**Determines if the file exists,
         * returns the XXX object if it does,
         * prints the log and throws an exception if it doesn't
         * */
        if (file.exists()){
            RequestBody requestBody = RequestBody.create(parse, file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", "asd.jpg", requestBody);
            return part;
            
        }else {
            Log.d(TAG, "getMultiPart: 文件不存在");
            throw new IllegalArgumentException("文件不存在");
        }
    }

    public MultipartBody.Part getMultiPart(File file){
        MediaType parse = MediaType.parse("application/octet-stream");

        /**Determines if the file exists,
         * returns the XXX object if it does,
         * prints the log and throws an exception if it doesn't
         * */
        if (file.exists()){
            RequestBody requestBody = RequestBody.create(parse, file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", "asd.jpg", requestBody);
            return part;

        }else {
            Log.d(TAG, "getMultiPart: 文件不存在");
            throw new IllegalArgumentException("文件不存在");
        }
    }
}
