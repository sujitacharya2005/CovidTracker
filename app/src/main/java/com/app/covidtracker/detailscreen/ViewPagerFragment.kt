package com.app.covidtracker.detailscreen

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerFragment(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CounryStatusFragment()
            1 -> DateWiseFragment()
            else -> throw IllegalStateException()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title = "";
        if (position == 0)
            title = "Country Status";
        else if (position == 1)
            title = "Date Wise Status";

        return title;
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }

}