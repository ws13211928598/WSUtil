package com.example.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * created by ws
 * on 2020/12/30
 * describe:
 */
public class Utilws {
    private static final String TAG = "asd";
    public String ws = "asd";
    Context context;
    private Uri uri;

    public Utilws(Context context) {
        this.context = context;
    }

    /**Pass in context, layout, Toolbar,R.string.app.name*/
    public void actionBarDrawerToggleWs(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int stringName){
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar, stringName, stringName);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
    /**Passing in context,Fragment collection, TabName collection*/
    public void viewpager2Ws(FragmentActivity this_Context, ViewPager2 viewPager2, TabLayout tabLayout, ArrayList<Fragment> fragments, int limit, ArrayList<String> tabItem){
        viewPager2.setAdapter(new FragmentStateAdapter(this_Context) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });
        viewPager2.setOffscreenPageLimit(limit);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabItem.get(position));

            }
        }).attach();
    }
    public PopupWindow popupWindowWs(View inflate){
        PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setOutsideTouchable(true);
        return popupWindow;
    }

    public VideoView videoViewWs(VideoView videoView,String pathVideo){
        videoView.setVideoPath(pathVideo);
        MediaController mediaController = new MediaController(context);
        videoView.setMediaController(mediaController);
        return videoView;
    }




}
