package com.aas.kantinqu.ui.profile

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.aas.kantinqu.FoodMarket
import com.aas.kantinqu.R
import com.aas.kantinqu.model.response.ProfileResponse
import com.aas.kantinqu.model.response.login.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson

class ProfileFragment : Fragment(), ProfileContract.View{

    lateinit var presenter: ProfilePresenter
    var progressDialog: Dialog? = null

    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var ivPicture: ImageView
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvName = requireView().findViewById(R.id.tvName)
        tvEmail = requireView().findViewById(R.id.tvEmail)
        ivPicture = requireView().findViewById(R.id.ivPicture)
        viewPager = requireView().findViewById(R.id.viewPager)
        tabLayout = requireView().findViewById(R.id.tabLayout)

        initView()
        presenter = ProfilePresenter(this)
        presenter.getProfile()

        var user = FoodMarket.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        tvName.text = userResponse.name
        tvEmail.text = userResponse.email

        if (!userResponse.profile_photo_url.isNullOrEmpty()) {
            Glide.with(requireActivity())
                .load(userResponse.profile_photo_url)
                .circleCrop()
                .into(ivPicture)
        }

        val sectionPagerAdapter = SectionPagerAdapter(childFragmentManager, 2)
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = sectionPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onProfileSuccess(profileResponse: ProfileResponse) {
        tvName.text = profileResponse.name
        tvEmail.text = profileResponse.email
        Glide.with(this)
            .load(profileResponse.profilePhotoUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(ivPicture)
    }

    override fun onProfileFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}