package com.pam.kantinqu.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.pam.kantinqu.ui.detail.DetailActivity
import com.pam.kantinqu.FoodMarket
import com.pam.kantinqu.R
import com.pam.kantinqu.model.response.home.Data
import com.pam.kantinqu.model.response.home.HomeResponse
import com.pam.kantinqu.model.response.login.User
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback, HomeContract.View{

    private var newTasteList: ArrayList<Data> = ArrayList()
    private var popularList: ArrayList<Data> = ArrayList()
    private var recommendedList: ArrayList<Data> = ArrayList()
    private lateinit var ivProfile: ImageView

    private lateinit var presenter: HomePresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()

        presenter = HomePresenter(this)
        presenter.getHome()

    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        ivProfile = view?.findViewById(R.id.ivProfile) ?: return
        var user = FoodMarket.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        if (!userResponse.profile_photo_url.isNullOrEmpty()) {
            Glide.with(requireActivity())
                .load(userResponse.profile_photo_url)
                .into(ivProfile)
        }

    }

//    fun initDataDummy() {
//        foodList = ArrayList()
//        foodList.add(HomeModel("Cherry Healthy", "",5f))
//        foodList.add(HomeModel("Burger Tamayo", "",4f))
//        foodList.add(HomeModel("Bakwan Cihuy", "",4.5f))
//    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {

        for (a in homeResponse.data.indices){

            var items:List<String> = homeResponse.data[a].types?.split(",")?: ArrayList()
            for(x in items.indices) {

                if (items[x].equals("new_food", true)) {
                    newTasteList.add(homeResponse.data[a])
                } else if (items[x].equals("recommended", true)) {
                    recommendedList.add(homeResponse.data[a])
                } else if (items[x].equals("popular", true)) {
                    popularList.add(homeResponse.data[a])
                }
            }
        }

        val rcList: RecyclerView = view?.findViewById(R.id.rcList) ?: return
        var adapter = HomeAdapter(homeResponse.data, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter

        val viewPager: ViewPager = view?.findViewById(R.id.viewPager) ?: return
        val tabLayout: TabLayout = view?.findViewById(R.id.tabLayout) ?: return

        val sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        sectionPagerAdapter.setData(newTasteList, popularList, recommendedList)
        viewPager.adapter = sectionPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}