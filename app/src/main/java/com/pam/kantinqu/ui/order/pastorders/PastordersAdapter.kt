package com.pam.kantinqu.ui.order.pastorders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pam.kantinqu.R
import com.pam.kantinqu.model.response.transaction.Data
import com.pam.kantinqu.utils.Helpers.convertLongToTime
import com.pam.kantinqu.utils.Helpers.formatPrice
import com.bumptech.glide.Glide

class PastordersAdapter (
    private val listData: List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback
    ) : RecyclerView.Adapter<PastordersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pastorders, parent, false)
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
        private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        private val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        private val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)

        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                tvTitle.text = data.food.name
                tvPrice.formatPrice(data.food.price.toString())
                tvDate.text = data.createdAt.convertLongToTime("MMM dd , HH.mm")

                Glide.with(context)
                    .load(data.food.picturePath)
                    .into(ivPoster)

                if (data.status.equals("CANCELLED", true)){
                    tvStatus.visibility = View.VISIBLE
                    tvStatus.text = data.status
                }

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