package com.aas.kantinqu.ui.home.newtaste

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aas.kantinqu.ui.detail.DetailActivity
import com.aas.kantinqu.R
import com.aas.kantinqu.model.response.home.Data

class HomeNewTasteFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallback{

//    private  var foodList : ArrayList<HomeVerticalModel> = ArrayList()
    private var newTasteList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newTasteList = arguments?.getParcelableArrayList("data")
        val rcList: RecyclerView = view?.findViewById(R.id.rcList) ?: return

//        initDataDummy()
        var adapter = HomeNewTasteAdapter(newTasteList!!, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }

//    fun initDataDummy() {
//        foodList = ArrayList()
//        foodList.add(HomeVerticalModel("Cherry Healthy", "10000",5f))
//        foodList.add(HomeVerticalModel("Burger Tamayo", "10000",4f))
//        foodList.add(HomeVerticalModel("Bakwan Cihuy", "10000",4.5f))
//    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}