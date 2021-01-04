package com.example.mylibrary;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * created by ws
 * on
 * describe:
 */
public class WsFragmentManager {

    private final FragmentManager fragmentManager;

    public WsFragmentManager(Activity activity) {
        fragmentManager = activity.getFragmentManager();
    }

    public void fragmentManagerCreate( ArrayList<Fragment> fragments, int layout){

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            fragmentTransaction.add(layout,fragment);
            if (i==0){
                fragmentTransaction.show(fragment);
            }else {
                fragmentTransaction.hide(fragment);
            }
        }
        fragmentTransaction.commit();

    }

    public void fragmentShow(ArrayList<Fragment> fragments, int position){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if (i == position){
                fragmentTransaction.show(fragment);
            }else {
                fragmentTransaction.hide(fragment);
            }
            fragmentTransaction.commit();
        }
    }
}
