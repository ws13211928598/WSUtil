package com.example.mylibrary;

import android.content.Context;
import android.os.Looper;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * created by ws
 * on 2020/12/30
 * describe:
 */
public class WsDownload {
    Context context;

    public WsDownload(Context context) {
        this.context = context;

    }

    public void download(InputStream inputStream, long max, String savePath){

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(savePath);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes,0,len);
            }
            inputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
    /storage/emulated/0/xxx.jpg(xxx.apk)
            /storage/emulated/0/mypicter/y42.jpg*/
    public void download(InputStream inputStream, long max, String savePath, ProgressBar progressBar){

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(savePath);
            byte[] bytes = new byte[1024];
            int len;
            int progress = 0;
            while ((len = inputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes,0,len);
                progress += len;
                progressBar.setMax((int) max);
                progressBar.setProgress(progress);
                EventBus.getDefault().postSticky(progress);
            }

            inputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
