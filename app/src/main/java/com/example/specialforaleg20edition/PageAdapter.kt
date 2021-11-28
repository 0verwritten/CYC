package com.example.specialforaleg20edition

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return Home()
            }
            1 -> {
                return Add()
            }
            2 -> {
                return ShopList()
            }
            3 -> {
                return Diet()
            }
            else -> {
                return Home()
            }
        }
    }
    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Home"
            }
            1 -> {
                return "Add"
            }
            2 -> {
                return "Shop List"
            }
            3 -> {
                return "Diet"
            }
        }
        return super.getPageTitle(position)
    }
}