package com.example.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

/**
 * created by ws
 * on 2021/1/3
 * describe:
 */
public class SuperImageWs {
    Context context;
    private Uri uri;

    public SuperImageWs(Context context) {
        this.context = context;
    }

    public File camera(int requestCode){
        File file = new File(context.getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
            uri = Uri.fromFile(file);
        }else {
            uri = FileProvider.getUriForFile(context,context.getPackageName(),file);
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        Activity context = (Activity) this.context;
        context.startActivityForResult(intent,requestCode);
        return file;
    }

    public void openAlbum(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        Activity context = (Activity) this.context;
        context.startActivityForResult(intent,11);
    }


   /**
    * Uri imageUri = data.getData();
    //Handle urIs. After 7.0, fileProviders provide uri resolution as a content Provider
    File file = getFileFromUri(imageUri, this);
                if (file.exists()) {
    getMultiPart(file);*/
    }

