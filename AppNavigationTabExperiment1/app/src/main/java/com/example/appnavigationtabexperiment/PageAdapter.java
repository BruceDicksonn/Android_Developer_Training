package com.example.appnavigationtabexperiment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0: return new TabFragment1();
            case 1: return new TabFragment2();
            case 2: return new TabFragment3();

            default: return null;

        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
