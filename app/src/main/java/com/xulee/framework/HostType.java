package com.xulee.framework;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Desc: host类型
 * Created by xulee on 2017/3/13
 */

public class HostType {

    /**
     * 多少种Host类型
     */
    public static final int TYPE_COUNT = 2;

    /**
     * 摇的host
     */
    @HostTypeChecker
    public static final int YAO = 1;

    /**
     * Cms的host
     */
    @HostTypeChecker
    public static final int CMS = 2;

    /**
     * 替代枚举的方案，使用IntDef保证类型安全
     */
    @IntDef({YAO, CMS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HostTypeChecker {

    }
}
