package com.pam.kantinqu.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.pam.kantinqu.model.response.transaction.Data
import com.pam.kantinqu.ui.order.inprogress.InprogressFragment
import com.pam.kantinqu.ui.order.pastorders.PastordersFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var inprogressList:ArrayList<Data> ?= ArrayList()
    var pastordersList:ArrayList<Data> ?= ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "In Progress"
            1 -> "Past Orders"
            else -> ""

        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        val fragment: Fragment
        return when (position) {
            0 -> {
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                InprogressFragment().apply { arguments = bundle }
            }
            1 -> {
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", pastordersList)
                PastordersFragment().apply { arguments = bundle }
            }
            else -> {
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                InprogressFragment().apply { arguments = bundle }
            }
        }
    }

    fun setData(inprogressListParms:ArrayList<Data>?, pastordersListParms:ArrayList<Data>?) {
        inprogressList = inprogressListParms
        pastordersList = pastordersListParms
    }
}