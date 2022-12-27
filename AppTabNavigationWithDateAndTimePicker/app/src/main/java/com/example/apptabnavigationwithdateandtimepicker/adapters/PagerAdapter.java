package com.example.apptabnavigationwithdateandtimepicker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.apptabnavigationwithdateandtimepicker.view.TabFragmentAlert;
import com.example.apptabnavigationwithdateandtimepicker.view.TabFragmentDate;
import com.example.apptabnavigationwithdateandtimepicker.view.TabFragmentTimer;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0: return new TabFragmentAlert();
            case 1: return new TabFragmentDate();
            case 2: return new TabFragmentTimer();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
