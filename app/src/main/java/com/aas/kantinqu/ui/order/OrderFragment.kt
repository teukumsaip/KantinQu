package com.aas.kantinqu.ui.order

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.aas.kantinqu.R
import com.aas.kantinqu.model.response.transaction.Data
import com.aas.kantinqu.model.response.transaction.TransactionResponse
import com.google.android.material.tabs.TabLayout

class OrderFragment : Fragment(), OrderContract.View {

    lateinit var presenter: OrderPresenter
    var progressDialog: Dialog? = null

    var inprogressList: ArrayList<Data>? = ArrayList()
    var pastordersList: ArrayList<Data>? = ArrayList()

    private lateinit var ll_empty: LinearLayout
    private lateinit var cl_tab: CoordinatorLayout
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var include_toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_order, container, false)

        // Inisialisasi variabel berdasarkan ID yang telah didefinisikan di XML
        ll_empty = root.findViewById(R.id.ll_empty)
        cl_tab = root.findViewById(R.id.cl_tab)
        viewPager = root.findViewById(R.id.viewPager)
        tabLayout = root.findViewById(R.id.tabLayout)
        include_toolbar = root.findViewById(R.id.toolbar) as Toolbar

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = OrderPresenter(this)
        presenter.getTransaction()

        initView()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        include_toolbar.title = "Your Orders"
        include_toolbar.subtitle = "Wait for the best meal"

    }

    override fun onTransactionSuccess(transactionResponse: TransactionResponse) {
        if (transactionResponse.data.isNullOrEmpty()) {
            ll_empty.visibility = View.VISIBLE
            cl_tab.visibility = View.GONE
            include_toolbar.visibility = View.GONE
        } else {
            for (a in transactionResponse.data.indices) {
                if (transactionResponse.data[a].status.equals("ON_DELIVERY",true)
                    || transactionResponse.data[a].status.equals("PENDING", true)) {
                    inprogressList?.add(transactionResponse.data[a])
                } else if (transactionResponse.data[a].status.equals("DELIVERED",true)
                    || transactionResponse.data[a].status.equals("CANCELLED",true)
                    || transactionResponse.data[a].status.equals("SUCCESS", true)) {
                    pastordersList?.add(transactionResponse.data[a])
                }
            }
            val sectionPagerAdapter = SectionPagerAdapter(
                childFragmentManager
            )
            sectionPagerAdapter.setData(inprogressList, pastordersList)
            viewPager.adapter = sectionPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    override fun onTransactionFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}