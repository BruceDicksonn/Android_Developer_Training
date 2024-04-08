package com.example.learntonavigation.ui.views.commons

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentPageAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    private val listFragments = arrayListOf<Fragment>()
    private val listFragmentsTitle = arrayListOf<String>()

    override fun getCount(): Int {
        return listFragments.count()
    }

    fun add(tabFragment: Fragment, tabTitle: String) {
        if (!listFragments.contains(tabFragment)) {
            listFragments.add(tabFragment)
            listFragmentsTitle.add(tabTitle)
        }
    }

    override fun getItem(position: Int): Fragment {
        return listFragments.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listFragmentsTitle.get(position)
    }

}