package com.jafarcode.apotekcemerlangfarma.ui.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jafarcode.apotekcemerlangfarma.databinding.ItemHomeHorizontalBinding
import com.jafarcode.apotekcemerlangfarma.model.response.home.Data


class HomeAdapter (
    private  val listData : List<Data>,
    private  val itemAdapterCallback: ItemAdapterCallback,
): RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):ViewHolder {
        val binding = ItemHomeHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
       return listData.size
    }

    class ViewHolder (val binding:ItemHomeHorizontalBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind (data: Data,itemAdapterCallback: ItemAdapterCallback) {
            with(binding) {
                tvTitle.text = data.name
               /* rbDrug.rating = data.rate?.toFloat() ?: 0f*/

                Glide.with(binding.root.context)
                    .load(data.picturePath)
                    .into(ivPoster)
                binding.root.setOnClickListener{itemAdapterCallback.onClick(it, data)}


            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data:Data)

    }
}