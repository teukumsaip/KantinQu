package com.pam.kantinqu.ui.order.inprogress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pam.kantinqu.R
import com.pam.kantinqu.model.response.transaction.Data

class InprogressFragment : Fragment(), InprogressAdapter.ItemAdapterCallback {

    private var adapter: InprogressAdapter? = null
    var inprogressList: ArrayList<Data>? = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inprogress, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        inprogressList = arguments?.getParcelableArrayList("data")
        val rcList: RecyclerView = view?.findViewById(R.id.rcList) ?: return

        if (!inprogressList.isNullOrEmpty()) {
            adapter = InprogressAdapter(inprogressList!!, this)
            val layoutManager = LinearLayoutManager(activity)
            rcList.layoutManager = layoutManager
            rcList.adapter = adapter
        }
    }

    override fun onClick(v: View, data: Data) {
        Toast.makeText(activity,"coba klik", Toast.LENGTH_LONG).show()
    }


}