package com.example.teste;

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

            case 0: return new TabStatus();
            case 1: return new TabChamadas();
            case 2: return new TabCamera();
            case 3: return new TabConversas();
            case 4: return new TabConfiguracoes();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return this.mNumOfTabs;
    }
}
