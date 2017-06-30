package com.ikould.frame.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 包相关的查询工具
 * <p>
 * Created by ikould on 2016/7/28.
 */
public class PackageUtil {

    /**
     * 判断当前应用是否在前台
     *
     * @param context 上下文
     * @return
     */
    public static boolean isCurrentAppOnForeground(Context context) {
        String currentPackageName = context.getPackageName();
        return isPackageOnForeground(context, currentPackageName);
    }

    /**
     * 判断应用是否在前台
     *
     * @param context 上下文
     * @param pckName 包名
     * @return
     */
    public static boolean isPackageOnForeground(Context context, String pckName) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        String packageName = info.topActivity.getPackageName();
        if (pckName.equals(packageName)) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前应用版本名
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前应用版本号
     *
     * @param application
     * @return
     */
    public static int getVersionCode(Application application) {
        PackageManager packageManager = application.getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(application.getPackageName(), 0);
            return packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
