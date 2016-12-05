package com.example.android_tfw_retrofit2_mvp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android_tfw_retrofit2_mvp.R;
import com.example.android_tfw_retrofit2_mvp.utils.AppManager;
import com.example.android_tfw_retrofit2_mvp.utils.CustomToast;
import com.example.android_tfw_retrofit2_mvp.view.MyDialog;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Activity抽象基类
 */
public  class BaseActivity extends AppCompatActivity {
    // 上下文实例
    public Context context;
    // 应用全局的实例
    public MyApplication application;


    // 核心层的accountAction实例
//    public AccountAction accountAction;
//    // 核心层的appAction实例
//    public AppAction appAction;
//    // 队列任务的Action实例
//    public QueueAction queueAction;

    public Context mContext = BaseActivity.this;
    public SharedPreferences mSharedPreferences, defaultSharedPreferences;
    public SharedPreferences.Editor mSharedPreferencesEditor;
    public String userID, xueduan, sid;//用户id  xueduan 设为全局的
    //加载动画对话框
    public MyDialog alertDialog;
    //列表加载动画显示控件
    private static View view;

//    public abstract void onRegisterCloseListener(); // 注册当前ACtivity
//
//    public abstract void onUnRegisterCloseListener(); // 解除


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        application = (MyApplication) this.getApplication();
        //每打开一个新的页面都加到栈里
        AppManager.getAppManager().addActivity(this);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //通知栏颜色随app改变
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//        }else{
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

        registerCloseListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
//        onRegisterCloseListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
//        onUnRegisterCloseListener();
    }


    //加载动画
    protected void initLoading() {
        if (alertDialog != null) {
            alertDialog.show();
        } else {
            alertDialog = new MyDialog(BaseActivity.this);
            // 点击屏幕 不消失
//            alertDialog.setCancelable(false);
            View view = LayoutInflater.from(BaseActivity.this).inflate(R.layout.loadinglayout,
                    null);

            ImageView imageView = (ImageView) view.findViewById(R.id.loading_view);
            imageView.setImageResource(R.drawable.loading);
            AnimationDrawable drawable = (AnimationDrawable) imageView.getDrawable();
            drawable.start();
            alertDialog.setContentView(view);
            alertDialog.show();
        }
    }

    protected void cancelLoading() {
        if (alertDialog != null)
            alertDialog.dismiss();
    }


    /**
     * 获取所有未关闭的activity的对象集合
     *
     * @return 所有未关闭的activity的对象集合
     */
    public CopyOnWriteArrayList<Activity> getCloseListeners() {
        return application.getCloseListeners();
    }

    /**
     * 注册当前activity
     *
     * @param at activity对象
     */
    public void registerCloseListener(Activity at) {
        if (getCloseListeners() != null) {
            getCloseListeners().add(at);
        }
    }

    /**
     * 解注册activity
     *
     * @param at activity对象
     */
    public void unRegisterCloseListener(Activity at) {
        if (getCloseListeners() != null && getCloseListeners().contains(at)) {
            getCloseListeners().remove(at);
        }
    }

    /**
     * 启动Activity并关闭上一个Activity
     *
     * @param cls
     */
    public void startActivityAndFinish(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
        overridePendingTransition(R.anim.fade, R.anim.hold);
        closeActivity();
    }

    /**
     * 启动Activity，不关闭上一个Activity
     *
     * @param cls
     */
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
        overridePendingTransition(R.anim.fade, R.anim.hold);
    }

    /**
     * 关闭当前Activity
     */
    public void closeActivity() {
        finish();
    }

    public void showToast(String text) {
        CustomToast.show(context, text, 1000);
    }


}
