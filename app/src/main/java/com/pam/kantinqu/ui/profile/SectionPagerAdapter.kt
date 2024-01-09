package com.pam.kantinqu.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.pam.kantinqu.ui.profile.account.ProfileAccountFragment
import com.pam.kantinqu.ui.profile.foodmarket.FoodMarketFragment

class SectionPagerAdapter(fm: FragmentManager?, private val number_tabs: Int) :
    FragmentPagerAdapter(fm!!) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Account"
            1 -> "FoodMarket"
            else -> ""

        }
    }

    override fun getCount(): Int {
        return number_tabs
    }

    override fun getItem(position: Int): Fragment {
        val fragment: Fragment
        return when (position) {
            0 -> {
                fragment = ProfileAccountFragment()
                return fragment
            }
            1 -> {
                fragment = FoodMarketFragment()
                return fragment
            }
            else -> {
                fragment = ProfileAccountFragment()
                return fragment
            }
        }
    }
}