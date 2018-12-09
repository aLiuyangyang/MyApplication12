package com.example.dell.myapplication12;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dell.myapplication12.view.FragmentMy;
import com.example.dell.myapplication12.view.FragmentShow;

class MyTabBase extends FragmentPagerAdapter {
    String[] name=new String[]{"首页","我的"};
    public MyTabBase(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new FragmentShow();
            case 1:
                return new FragmentMy();
                default:
                    return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return name[position];
    }

    @Override
    public int getCount() {
        return name.length;
    }
}
