package com.example.utils.ext;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;
import java.util.List;

public class AppTools {
	/** 检测是否安装了某个应用 */
	public static boolean checkApkIsInstall(Context context, String packageName) {
		if (TextUtils.isEmpty(packageName)) {
			return false;
		}
		try {
			context.getPackageManager().getPackageInfo(packageName,
					PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/** 调用电话 */
	public static void callPhone(Context context, String mobile) {
		if (!TextUtils.isEmpty(mobile)) {
			Intent intent2 = new Intent();
			intent2.setAction(Intent.ACTION_DIAL);
			intent2.setData(Uri.parse("tel:" + mobile));
			context.startActivity(intent2);
		} else {
			ToastUtils.show(context, "未提供手机号!");
		}
	}
	/** 调用短信 */
	public static void sendMessage(Context context, String mobile,String content) {
//		if (!TextUtils.isEmpty(mobile)) {
		try{
			Uri smsToUri = Uri.parse("smsto:");
			Intent sendIntent = new Intent(Intent.ACTION_VIEW, smsToUri);
			sendIntent.putExtra("address", mobile); //电话号码，这行去掉的话，默认就没有电话
			sendIntent.putExtra("sms_body",content);
			sendIntent.setType("vnd.android-dir/mms-sms");
			context.startActivity(sendIntent);
		}catch (Exception e){
			e.printStackTrace();
		}

		/*} else {
			ToastUtils.show(context, "未提供手机号!");
		}*/
	}

	/**
	 * 判断应用是否在运行
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isAppRunning(Context context) {
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasks = activityManager
				.getRunningTasks(Integer.MAX_VALUE);
		for (RunningTaskInfo task : tasks) {
			if (context.getPackageName()
					.equalsIgnoreCase(task.baseActivity.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 *  获取应用版本名
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		String versionName = "";
		try {
			versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			versionName = "";
		}
		return versionName;
	}

	/**
	 * 获取程序版本号
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		int versionCode = 0;
		try {
			versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			versionCode = 0;
		}
		return versionCode;
	}

	/**
	 * 获取系统状态栏的高度
	 */
	public static int getStatusHeight(Context context){
		int statusHeight = 0;
		if (0 == statusHeight){
			Class<?> localClass;
			try {
				localClass = Class.forName("com.android.internal.R$dimen");
				Object localObject = localClass.newInstance();
				Field field = localClass.getField("status_bar_height");
				int i5 = Integer.parseInt(field.get(localObject).toString());
				statusHeight = context.getResources().getDimensionPixelSize(i5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(statusHeight==0){
			DisplayMetrics dis = context.getResources().getDisplayMetrics();
			statusHeight = (int) (25*dis.density);
		}
		return statusHeight;
	}

	public static boolean isForeground(Context context) {

		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
		for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.processName.equals(context.getPackageName())) {
                return appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
			}
		}
		return false;
	}
}
