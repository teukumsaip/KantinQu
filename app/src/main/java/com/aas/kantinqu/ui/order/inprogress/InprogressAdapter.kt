package com.aas.kantinqu.ui.order.inprogress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aas.kantinqu.R
import com.aas.kantinqu.model.response.transaction.Data
import com.aas.kantinqu.utils.Helpers.formatPrice
import com.bumptech.glide.Glide

class InprogressAdapter (
    private val listData: List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback
    ) : RecyclerView.Adapter<InprogressAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_inprogress, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {

            itemView.apply {
                tvTitle.text = data.food.name
                tvPrice.formatPrice(data.food.price.toString())

                Glide.with(context)
                    .load(data.food.picturePath)
                    .into(ivPoster)

                itemView.setOnClickListener{
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data: Data)
    }

}