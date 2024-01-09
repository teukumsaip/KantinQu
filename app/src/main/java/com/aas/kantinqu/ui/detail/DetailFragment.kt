package com.aas.kantinqu.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.aas.kantinqu.R
import com.aas.kantinqu.model.response.home.Data
import com.aas.kantinqu.utils.Helpers.formatPrice
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {

    var bundle:Bundle ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Mengatur toolbar jika diperlukan
        (activity as? DetailActivity)?.toolbarDetail()

        val data = arguments?.getParcelable<Data>("data")

        initView(data)

        val btnOrderNow: Button = requireView().findViewById(R.id.btnOrderNow)
        btnOrderNow.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_payment, bundle)
        }
    }

    private fun initView(data: Data?) {

        bundle = bundleOf("data" to data)

        // Inisialisasi semua view yang diperlukan dari layout fragment_detail
        val ivPoster: ImageView = requireView().findViewById(R.id.ivPoster)
        val tvTitle: TextView = requireView().findViewById(R.id.tvTitle)
        val tvDesc: TextView = requireView().findViewById(R.id.tvDesc)
        val tvIngredients: TextView = requireView().findViewById(R.id.tvIngredients)
        val rbFood: RatingBar = requireView().findViewById(R.id.rbFood)
        val tvRate: TextView = requireView().findViewById(R.id.tvRate)
        val tvTotal: TextView = requireView().findViewById(R.id.tvTotal)

        data?.let {
            Glide.with(requireContext())
                .load(it.picturePath)
                .into(ivPoster)

            tvTitle.text = it.name
            tvDesc.text = it.description
            tvIngredients.text = it.ingredients

            rbFood.rating = it.rate?.toFloat() ?: 0f
            tvRate.text = it.rate?.toFloat()?.toString() ?: "0"
            tvTotal.formatPrice(it.price.toString())
        }
    }
}