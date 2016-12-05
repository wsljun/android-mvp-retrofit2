package com.example.android_tfw_retrofit2_mvp.base;

import android.app.Activity;
import android.app.Application;
import android.provider.Settings;
import android.view.WindowManager;

import com.example.android_tfw_retrofit2_mvp.api.ApiConfing;
import com.example.android_tfw_retrofit2_mvp.utils.DisplayUtil;
import com.example.android_tfw_retrofit2_mvp.utils.L;
import com.example.android_tfw_retrofit2_mvp.utils.SystemUtil;
import com.example.android_tfw_retrofit2_mvp.utils.ValueStorage;
import com.orhanobut.logger.Logger;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 李均 on 2016/11/14.
 */

public class MyApplication extends Application {
    private WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams();
    //activity对象集合
    private volatile CopyOnWriteArrayList<Activity> closeListeners = new CopyOnWriteArrayList<>();

    public WindowManager.LayoutParams getWindowParams() {
        return windowParams;
    }
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
//        refWatcher = LeakCanary.install(this);
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
//        if(!BuildConfig.DEBUG){
//            L.disableDebug();
//        }else{
//            L.enableDebug();
//        }

          // 初始化logger
        Logger.init("lijun");
        ApiConfing.initBaseUrl(true); //debug 模式
        SystemUtil.init(this);
        DisplayUtil.init(this);
        ValueStorage.init(this);
//        L.enableDebug();
    }

    /**
     * 获取所有未关闭的activity的对象集合
     *
     * @return 所有未关闭的activity的对象集合
     */
    public CopyOnWriteArrayList<Activity> getCloseListeners() {
        if (closeListeners == null) {
            closeListeners = new CopyOnWriteArrayList<>();
        }
        return closeListeners;
    }

    /**
     * 退出系统
     */
    public void exitSystem() {
        for (Activity at : closeListeners) {
            if (!at.isDestroyed()) {
                at.finish();
            }
        }

        //强制退出当前进程，以确保App本身资源得到释放
        System.exit(0);
    }


}
