package com.example.mylibrary.utils.newAdd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.File;
import java.lang.reflect.Field;
import java.util.UUID;

public class SystemUtils {

    /**
     * 获取版本号
     */
    public static String getVersion(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = info.versionName;
        return version;
    }

    /**
     * 获取User_agent
     */
    public static String getUserAgent(Context context) {
        WebView wv = new WebView(context.getApplicationContext());
        WebSettings settings = wv.getSettings();
        String user_agent = settings.getUserAgentString();
        wv.destroy();
        return user_agent;
    }

    /**
     * 唯一机器码
     * 已经在base中增加权限方法
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceId(Context context) {
        //android.permission.READ_PHONE_STATE.
        //!!!必需在AndroidManifest.xml中声明，非常必要，否则部分手机无权限，无法获得IMEI
        String deviceId = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = telephonyManager.getDeviceId();
            if (deviceId == null)
                throw new NullPointerException();
        } catch (NullPointerException e) { // 如果设备唯一码没有的时候就获取mac地址
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            deviceId = info.getMacAddress();
        } catch (Exception e) { // 如果设备唯一码没有的时候就获取mac地址
            e.printStackTrace();
        }
        //如果Mac地址为空或者为默认值,6.0之上mac会获取默认值
        if (TextUtils.isEmpty(deviceId) || deviceId.equals("02:00:00:00:00:00")) {
            deviceId = SharedPrefrenceUtils.getString(context, "deviceId");
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = UUID.randomUUID().toString();
                SharedPrefrenceUtils.saveString(context, "deviceId", deviceId);
            }
        }
        return deviceId;

    }

    /**
     * 获取设备名称
     */
    public static String getDeviceName() {
        return Build.MODEL;

    }

    /**
     * 获取系统版本
     */
    public static String getSystem(Context context) {
        return "android," + Build.VERSION.RELEASE;

    }

    /**
     * 获取屏幕相关信息
     */
    public static Display getDisplay(Activity mActivity) {
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        return display;
    }

    /**
     * 获取屏幕相关信息
     */
    public static Point getSize(Activity mActivity) {
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * 获取屏幕真实信息，包含顶部状态栏和底部导航栏
     */
    public static Point getRealSize(Activity mActivity) {
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealSize(size);
        } else {
            display.getSize(size);
        }
        return size;
    }

    /**
     * 判断应用是否在前台运行
     */
    public static boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (!TextUtils.isEmpty(currentPackageName)
                && currentPackageName.equals(context.getPackageName())) {
            return true;
        }
        return false;
    }

    /**
     * 根据uid获取头像
     */
    public static String getAvatarByUid(String uid) {
        if (TextUtils.isEmpty(uid)) {
            return "";
        }
        int fillNum = 9 - uid.length();
        StringBuilder uidBuilder = new StringBuilder();
        for (int i = 0; i < fillNum; i++) {
            uidBuilder.append("0");
        }
        uidBuilder.append(uid);
        String nowUid = uidBuilder.toString();
        String avatar = "https://avatar.zhulong.com/avatar/"
                + nowUid.subSequence(0, 3) + "/" + nowUid.substring(3, 5) + "/"
                + nowUid.substring(5, 7) + "/" + nowUid.substring(7)
                + "_avatar_big.jpg";

        return avatar;
    }

    // 获取手机状态栏高度
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 蓝牙是否打开
     *
     * @return
     */
    public static boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter.isEnabled();
    }

    /**
     * SDCard 总容量大小
     *
     * @return MB
     */
    public static long getTotalSize() {
        String sdcard = Environment.getExternalStorageState();
        String state = Environment.MEDIA_MOUNTED;
        File file = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(file.getPath());
        if (sdcard.equals(state)) {
            //获得sdcard上 block的总数
            long blockCount = statFs.getBlockCount();
            //获得sdcard上每个block 的大小
            long blockSize = statFs.getBlockSize();
            //计算标准大小使用：1024，当然使用1000也可以
            long bookTotalSize = blockCount * blockSize / 1000 / 1000;
            return bookTotalSize;

        } else {
            return -1;
        }

    }

    /**
     * 计算Sdcard的剩余大小
     *
     * @return byte
     */
    public static long getAvailableSize() {
        String sdcard = Environment.getExternalStorageState();
        String state = Environment.MEDIA_MOUNTED;
        File file = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(file.getPath());
        if (sdcard.equals(state)) {
            //获得Sdcard上每个block的size
            long blockSize = statFs.getBlockSize();
            //获取可供程序使用的Block数量
            long blockavailable = statFs.getAvailableBlocks();
            //计算标准大小使用：1024，当然使用1000也可以
            long blockavailableTotal = blockSize * blockavailable;
            return blockavailableTotal;
        } else {
            return -1;
        }
    }
}
