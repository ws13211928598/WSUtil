package com.example.mylibrary.ws;

import android.app.Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * created by ws
 * on
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

    /**
     * Pass in context, layout, Toolbar,R.string.app.name
     */
    public void actionBarDrawerToggleWs(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int stringName) {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar, stringName, stringName);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    /**
     * Passing in context,Fragment collection, TabName collection
     */
    public void viewpager2Ws(FragmentActivity this_Context,
                             ViewPager2 viewPager2,
                             TabLayout tabLayout,
                             ArrayList<Fragment> fragments,
                             int limit,
                             ArrayList<String> tabItem,
                             List<Integer> IconList) {
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
                tab.setIcon(IconList.get(position));
            }
        }).attach();
    }
    public void viewpager2Ws(FragmentActivity this_Context,
                             ViewPager2 viewPager2,
                             TabLayout tabLayout,
                             ArrayList<Fragment> fragments,
                             ArrayList<String> tabItem,
                             List<Integer> IconList) {
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
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabItem.get(position));
                tab.setIcon(IconList.get(position));
            }
        }).attach();
    }
    public void viewpager2Ws(FragmentActivity this_Context,
                             ViewPager2 viewPager2,
                             TabLayout tabLayout,
                             ArrayList<Fragment> fragments,
                             ArrayList<String> tabItem) {
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
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabItem.get(position));
            }
        }).attach();
    }

    /**
     * CustomReceiver1 customReceiver1 = new CustomReceiver1();
     * IntentFilter intentFilter = new IntentFilter("com.example.d1221.receiver");
     * intentFilter.setPriority(1);
     * registerReceiver(customReceiver1,intentFilter);
     * <p>
     * Intent intent = new Intent("com.example.d1221.receiver");
     * intent.putExtra("data","asdasd");
     * sendBroadcast(intent);
     */


    public void viewpager2Ws(FragmentActivity this_Context,
                             ViewPager2 viewPager2,
                             TabLayout tabLayout,
                             ArrayList<Fragment> fragments,
                             int limit,
                             ArrayList<String> tabItem) {
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



    public PopupWindow popupWindowWs(View inflate) {
        PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        return popupWindow;
    }

    //String s = "android.resource://" + getPackageName() + "/raw/s3";
    //  /storage/emulated/0/博雅东湖.mp4
    // /storage/emulated/0/Pictures/s1.mp4
    public VideoView videoViewWs(VideoView videoView, String pathVideo) {
        videoView.setVideoPath(pathVideo);
        MediaController mediaController = new MediaController(context);
        videoView.setMediaController(mediaController);
        return videoView;
    }

    public VideoView videoViewWs(VideoView videoView) {
        videoView.setVideoPath("/storage/emulated/0/Pictures/s1.mp4");
        MediaController mediaController = new MediaController(context);
        videoView.setMediaController(mediaController);
        return videoView;
    }

    public void notificationWs(String title, String desc, int icon, Intent intent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("id", "name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager systemService = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            systemService.createNotificationChannel(notificationChannel);
        }

        Notification id = new NotificationCompat.Builder(context, "id")
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(desc)
                .setContentIntent(PendingIntent.getActivity(context, 10, intent, PendingIntent.FLAG_CANCEL_CURRENT))
                .setAutoCancel(true)
                .build();
        NotificationManagerCompat from = NotificationManagerCompat.from(context);
        from.notify(11, id);

    }

    public void callPhone(int phone) {
        //Manifest.permission.CALL_PHONE
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        this.context.startActivity(intent);
    }


    /**
     * Use dangerous permissions before using
     * <uses-permission android:name="android.permission.READ_CONTACTS" />
     * <uses-permission android:name="android.permission.WRITE_CONTACTS" />
     * Manifest.permission.READ_CONTACTS
     * Manifest.permission.WRITE_CONTACTS
     */
    public ArrayList<TelBean> getLianXiRen() {
        ArrayList<TelBean> telBeans = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        String[] PROJECTION = {ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts._ID};
        Cursor phoneCursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, null, null, null);

        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                TelBean telBean = new TelBean();
                String telDisplayName = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                telBean.setName(telDisplayName);
                int hasTelNumber = phoneCursor.getInt(phoneCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                String contactId = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.Contacts._ID));
                if (!TextUtils.isEmpty(telDisplayName) && hasTelNumber == 1) {
                    Cursor phoneNumber = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
                    if (phoneNumber != null) {
                        while (phoneNumber.moveToNext()) {
                            String telNumber = phoneNumber.getString(phoneNumber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            telBean.setName(telNumber);
                            telBeans.add(telBean);
                        }
                        return telBeans;
                    }
                }

            }
        }
        return null;
    }

    public RequestBody getJsonRequestBody(JSONObject json){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
        return requestBody;
    }


}
