package com.tif.testoonjo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tif.testoonjo.activity.main.MainActivity
import com.tif.testoonjo.activity.main.MainActivity2

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MainActivity()
            }
            else -> {
                return MainActivity2()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Live Data"
            else -> {
                return "Offline Data"
            }
        }
    }
}