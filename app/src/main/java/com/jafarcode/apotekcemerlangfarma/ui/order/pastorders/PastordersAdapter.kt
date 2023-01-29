package com.jafarcode.apotekcemerlangfarma.ui.order.pastorders


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jafarcode.apotekcemerlangfarma.databinding.ItemPastordersBinding
import com.jafarcode.apotekcemerlangfarma.model.response.transaction.Data
import com.jafarcode.apotekcemerlangfarma.utils.Helpers.formatPrice


class PastordersAdapter (


    private  val listData : List<Data>,
    private  val itemAdapterCallback: ItemAdapterCallback,

): RecyclerView.Adapter<PastordersAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):ViewHolder {
        val binding = ItemPastordersBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
       return listData.size
    }

    class ViewHolder (val binding:ItemPastordersBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind (data: Data,itemAdapterCallback: ItemAdapterCallback) {

            with(binding) {
                tvTitle.text = data.drug.name
                tvPrice.formatPrice(data.total.toString())
                tvDate.text = data.createdAt/*.convertLongToTime("MM dd, HH.mm")*/
                tvCancelled.text = data.status
               /* rbDrug.rating = data.rate?.toFloat() ?: 0f*/

                Glide.with(binding.root.context)
                    .load(data.drug.picturePath)
                    .into(ivPoster )
                /*binding.root.setOnClickListener{itemAdapterCallback.onClick(it, data)}*/

                if ( data.status.equals("CANCELED", true)) {
                    tvCancelled.visibility = View.VISIBLE
                    tvCancelled.text = "Dibatalkan"
                }else if ( data.status.equals("SUCCESS", true)) {
                    tvCancelled.visibility = View.VISIBLE
                    tvCancelled.text = "Selesai"
                }

                binding.root.setOnClickListener{itemAdapterCallback.onClick(it, data)}
            }

        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data:Data)

    }
}