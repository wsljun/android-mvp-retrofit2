package com.example.android_tfw_retrofit2_mvp.view;

import android.app.Dialog;
import android.content.Context;

import com.example.android_tfw_retrofit2_mvp.R;

public class MyDialog extends Dialog {
    public MyDialog(Context context) {
        super(context, R.style.MyDialog);
        setContentView(R.layout.dialog_base);
    }

    //设置提示头
    public void setTitle(int titleId) {
        setTitle(getContext().getString(titleId));
    }

    //ok按钮的方法
    public void setBtn_ok(String content, final OnClickListener listener) {
//        mBtn_ok.setVisibility(View.VISIBLE);
//        mBtn_ok.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                isNeedDismiss = true;
//                if (listener != null) {
//                    listener.onClick(MyDialog.this, 1);
//                }
//                if (isNeedDismiss)
//                    dismiss();
//            }
//        });
    }

    //取消按钮的方法
    public void setBtn_cancel(String content, final OnClickListener listener) {
//        mBtn_cancel.setVisibility(View.VISIBLE);
//        mBtn_cancel.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                isNeedDismiss = true;
//                if (listener != null) {
//                    listener.onClick(MyDialog.this, 3);
//                }
//                if (isNeedDismiss)
//                    dismiss();
//            }
//        });
    }

    //取消按钮的背景
    public void setBtn_cancelBackground(int id) {
//        mBtn_cancel.setBackgroundResource(id);
    }

    //确定按钮的背景
    public void setBtn_okBackground(int id) {
//        mBtn_ok.setBackgroundResource(id);
    }

    public void setCanDismissByKeyBack(boolean isCanDismissByKeyBack) {
//        this.isCanDismissByKeyBack = isCanDismissByKeyBack;
    }

    public void setIsNeedDismiss(boolean isNeedDismiss) {
//        this.isNeedDismiss = isNeedDismiss;
    }

    public void setNegativeButtonGone() {
//        mBtn_cancel.setVisibility(View.GONE);
    }

    public void setPositiveButtonGone() {
//        mBtn_ok.setVisibility(View.GONE);
    }


}
