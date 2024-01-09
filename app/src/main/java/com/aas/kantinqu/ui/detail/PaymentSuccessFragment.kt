package com.aas.kantinqu.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.aas.kantinqu.R

class PaymentSuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_success, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()
        val btnOtherFood: Button = requireView().findViewById(R.id.btnOtherFood)
        val btnMyOrder: Button = requireView().findViewById(R.id.btnMyOrder)

        btnOtherFood.setOnClickListener {
            requireActivity().finish()
        }

        btnMyOrder.setOnClickListener {
            requireActivity().finish()
        }
    }

}