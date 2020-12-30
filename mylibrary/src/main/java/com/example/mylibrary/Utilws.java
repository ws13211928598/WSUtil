package com.example.mylibrary;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


/**
 * created by ws
 * on 2020/12/30
 * describe:
 */
public class Utilws {
    public String ws = "asd";

    public void ActionBarDrawerToggleWs(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int stringName){
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar, stringName, stringName);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
    public void Viewpager2Ws(FragmentActivity this_Context, ViewPager2 viewPager2, TabLayout tabLayout, ArrayList<Fragment> fragments, int limit, ArrayList<String> tabItem){
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
}
