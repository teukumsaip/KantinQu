package com.aas.kantinqu.ui.home.newtaste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aas.kantinqu.R
import com.aas.kantinqu.model.response.home.Data
import com.aas.kantinqu.utils.Helpers.formatPrice
import com.bumptech.glide.Glide

class HomeNewTasteAdapter(
    private val listData: ArrayList<Data>,
    private val itemAdapterCallback: ItemAdapterCallback,
) : RecyclerView.Adapter<HomeNewTasteAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewTasteAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeNewTasteAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val rbFood: RatingBar = itemView.findViewById(R.id.rbFood)
        private val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)

        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tvTitle.text = data.name
                tvPrice.formatPrice(data.price.toString())
                rbFood.rating = data.rate?.toFloat() ?: 0f

                Glide.with(context)
                    .load(data.picturePath)
                    .into(ivPoster)

                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data: Data)
    }

}