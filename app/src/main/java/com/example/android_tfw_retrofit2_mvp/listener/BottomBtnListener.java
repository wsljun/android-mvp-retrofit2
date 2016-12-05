package com.example.android_tfw_retrofit2_mvp.listener;

import android.view.View;

/**
 * Created by 李均 on 2016/11/22.
 */
public class BottomBtnListener implements View.OnClickListener {

        private int index=0;

        public BottomBtnListener(int i) {
            index =i;
        }
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
//            mPager.setCurrentItem(index);
        }
}
