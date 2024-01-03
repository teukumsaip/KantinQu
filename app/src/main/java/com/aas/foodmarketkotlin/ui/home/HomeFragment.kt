package com.aas.foodmarketkotlin.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.aas.foodmarketkotlin.R
import com.aas.foodmarketkotlin.model.dummy.HomeModel
import com.aas.foodmarketkotlin.ui.detail.DetailActivity
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback{

    private  var foodList : ArrayList<HomeModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val rcList: RecyclerView = view?.findViewById(R.id.rcList) ?: return

        initDataDummy()
        var adapter = HomeAdapter(foodList, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter

        val viewPager: ViewPager = view?.findViewById(R.id.viewPager) ?: return
        val tabLayout: TabLayout = view?.findViewById(R.id.tabLayout) ?: return

        val sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        viewPager.adapter = sectionPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
    fun initDataDummy() {
        foodList = ArrayList()
        foodList.add(HomeModel("Cherry Healthy", "",5f))
        foodList.add(HomeModel("Burger Tamayo", "",4f))
        foodList.add(HomeModel("Bakwan Cihuy", "",4.5f))
    }

    override fun onClick(v: View, data: HomeModel) {
        val detail = Intent(activity, DetailActivity::class.java)
        startActivity(detail)
    }
}