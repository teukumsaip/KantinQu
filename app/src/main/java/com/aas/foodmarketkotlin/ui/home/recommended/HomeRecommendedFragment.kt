package com.aas.foodmarketkotlin.ui.home.recommended

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aas.foodmarketkotlin.R
import com.aas.foodmarketkotlin.model.dummy.HomeVerticalModel
import com.aas.foodmarketkotlin.model.response.home.Data
import com.aas.foodmarketkotlin.ui.detail.DetailActivity
import com.aas.foodmarketkotlin.ui.home.newtaste.HomeNewTasteAdapter

class HomeRecommendedFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallback {

    private lateinit var ivProfile: ImageView
    private var recommendedList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ivProfile = view?.findViewById(R.id.ivProfile) ?: return
        recommendedList = arguments?.getParcelableArrayList("data")
        val rcList: RecyclerView = view?.findViewById(R.id.rcList) ?: return

        var adapter = HomeNewTasteAdapter(recommendedList!!, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }

}