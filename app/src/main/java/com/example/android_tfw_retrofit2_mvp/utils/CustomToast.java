package com.example.android_tfw_retrofit2_mvp.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;


public class CustomToast {
    private static Toast mToast;
    private static Handler mhandlder;
    private static Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mToast.cancel();
        }
    };

    /**
     * Toast 通知（当短时间内多个toast要弹出时， 直接显示最后一个）
     * @param context
     * @param text
     * @param duration
     */
    public static void show(Context context, String text, int duration) {
        if(mhandlder == null){
            mhandlder = new Handler();
        }
        mhandlder.removeCallbacks(mRunnable);
        if (mToast != null) {
            mToast.setText(text);
        } else {
            mToast = Toast.makeText(context, text, duration);
        }
        mhandlder.postDelayed(mRunnable, duration);
        mToast.show();
    }

    /**
     * Toast 通知（当短时间内多个toast要弹出时， 直接显示最后一个）
     * @param context
     * @param resID
     * @param duration
     */
    public static void show(Context context, int resID, int duration) {
        String text = context.getResources().getString(resID);
        show(context, text, duration);
    }

}
