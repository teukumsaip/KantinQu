package com.aas.foodmarketkotlin.ui.profile

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.aas.foodmarketkotlin.R
import com.google.android.material.tabs.TabLayout

class ProfileFragment : Fragment(){

    var progressDialog: Dialog? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root =  inflater.inflate(R.layout.fragment_profile, container, false)
        return  root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewPager: ViewPager = view?.findViewById(R.id.viewPager) ?: return
        val tabLayout: TabLayout = view?.findViewById(R.id.tabLayout) ?: return

        val sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        viewPager.adapter = sectionPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}