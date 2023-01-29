package com.jafarcode.apotekcemerlangfarma.ui.home.all



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jafarcode.apotekcemerlangfarma.databinding.ItemHomeVerticalBinding
import com.jafarcode.apotekcemerlangfarma.model.response.home.Data
import com.jafarcode.apotekcemerlangfarma.utils.Helpers.formatPrice


class HomeAllAdapter (
    private  val listData : List<Data>,
    private  val itemAdapterCallback: ItemAdapterCallback,
): RecyclerView.Adapter<HomeAllAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):ViewHolder {
        val binding = ItemHomeVerticalBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
       return listData.size
    }

    class ViewHolder (val binding:ItemHomeVerticalBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind (data: Data,itemAdapterCallback: ItemAdapterCallback) {
            with(binding) {
                tvTitle.text = data.name
                tvPrice.formatPrice(data.price.toString())
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