package com.example.apptrainingstepper.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.apptrainingstepper.ui.views.FirstPage;
import com.example.apptrainingstepper.ui.views.SecondPage;
import com.example.apptrainingstepper.ui.views.ThirdPage;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private int currentPageIndex = 0;
    public String[] pageName = new String[] { "Shipping Information","Second Page", "Third Page" };
    public boolean[] checkedPages = { true, false, false };

    public enum enumAction {
        NEXT,
        PREVIOUS
    }

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new SecondPage();
            case 2:
                return new ThirdPage();
            default:
                return new FirstPage();
        }
    }

    @Override
    public int getItemCount() { return 3; }
    public String getPageName(int position) {
        if(position == pageName.length) return pageName[2];
        return pageName[position];
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public boolean isChecked(int position) {
        int pos = (position < 0) ? 0 : (position == pageName.length) ? (pageName.length -1) : position;
        return checkedPages[pos];
    }

    public void checkPage(int position){
        checkedPages[position] = true;
    }
    public void uncheckPage(int position){
        checkedPages[position] = false;
    }

    public int next() {
        checkPage(currentPageIndex);

        if( (currentPageIndex + 1) < pageName.length) currentPageIndex += 1;

        return  currentPageIndex;
    }

    public int back() {
        uncheckPage(currentPageIndex);

        if( (currentPageIndex - 1) >= 0) currentPageIndex -= 1;

        return currentPageIndex;
    }


}
