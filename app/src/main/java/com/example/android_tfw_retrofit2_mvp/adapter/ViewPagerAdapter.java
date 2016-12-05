package com.example.android_tfw_retrofit2_mvp.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by 李均 on 2016/11/22.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    public ViewPagerAdapter(FragmentManager fm, @NonNull ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.list = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        Logger.d("destroyItem: "+position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Logger.d("instantiateItem: "+position);
        return super.instantiateItem(container, position);
    }
}
