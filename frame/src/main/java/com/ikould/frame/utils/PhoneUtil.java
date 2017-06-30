package com.ikould.frame.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

/**
 * 手机相关工具类
 * <p>
 * Created by ikould on 2017/6/5.
 */

public class PhoneUtil {

    /**
     * 判断系统是否安装了浏览器
     *
     * @param context
     * @return
     */
    public static boolean hasBrowser(Context context) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://"));
        List <ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY); // TAG 待定
        final int size = (list == null) ? 0 : list.size();
        return size > 0;
    }
}
