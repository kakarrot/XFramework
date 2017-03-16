package com.xulee.core.base;

import android.app.Application;
import android.content.Context;

import com.xulee.core.manager.AppManager;
import com.xulee.core.utils.AppUtils;

/**
 * Desc:
 * Created by xulee on 2017/3/13
 */

public class BaseApp extends Application {

    private static BaseApp app;

    protected AppManager appManager;
    private ActivityLifecycle activityLifecycle;

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.syncIsDebug(this);
        app = this;
        appManager = new AppManager(this);
        activityLifecycle = new ActivityLifecycle(appManager);
        registerActivityLifecycleCallbacks(activityLifecycle);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();

        if (activityLifecycle != null) {
            unregisterActivityLifecycleCallbacks(activityLifecycle);
        }

        if (appManager != null) {//释放资源
            this.appManager.release();
            this.appManager = null;
        }

        if (app != null) {
            app = null;
        }
    }

    public AppManager getAppManager() {
        return appManager;
    }

    public static Context getAppContext() {
        return app;
    }

}
