package com.xulee.core.utils;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * 如果使用BuildConfig.DEBUG 在moudle 中会一直为false， 因为moudle 会生成自己的buildConfig文件
 * 使用本类方法解决问题，见原文：http://mp.weixin.qq.com/s/H6egjDUjY61Z8aDfL3-QGw
 *
 * Created by xulee on 2017/3/13
 */
public class AppUtils {
    private static Boolean isDebug = null;

    public static boolean isDebug() {
        return null != isDebug && isDebug;
    }

    public static void syncIsDebug(Application context) {
        if(null == isDebug) {
            isDebug = (null != context.getApplicationInfo() && (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
        }
    }

}
