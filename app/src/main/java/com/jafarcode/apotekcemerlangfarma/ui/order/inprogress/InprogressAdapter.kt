package com.jafarcode.apotekcemerlangfarma.ui.order.inprogress


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jafarcode.apotekcemerlangfarma.databinding.ItemInprogressBinding
import com.jafarcode.apotekcemerlangfarma.model.response.transaction.Data

import com.jafarcode.apotekcemerlangfarma.utils.Helpers.formatPrice


class InprogressAdapter (
    private  val listData : List<Data>,
    private  val itemAdapterCallback: ItemAdapterCallback,
): RecyclerView.Adapter<InprogressAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder {
        val binding = ItemInprogressBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
       return listData.size
    }

    class ViewHolder (val binding:ItemInprogressBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind (data: Data,itemAdapterCallback: ItemAdapterCallback) {
            with(binding) {
                tvTitle.text = data.drug.name
                tvPrice.formatPrice(data.total.toString())
               /* rbDrug.rating = data.rate?.toFloat() ?: 0f*/

                Glide.with(binding.root.context)
                    .load(data.drug.picturePath)
                    .into(ivPoster)


                binding.root.setOnClickListener{itemAdapterCallback.onClick(it, data)}


            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data:Data)


    }
}