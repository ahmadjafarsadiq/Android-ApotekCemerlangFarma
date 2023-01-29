package com.jafarcode.apotekcemerlangfarma.ui.profile



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jafarcode.apotekcemerlangfarma.databinding.ItemMenuProfileBinding
import com.jafarcode.apotekcemerlangfarma.model.dummy.ProfileMenuModel



class ProfileMenuAdapter (

    private  val listData : List<ProfileMenuModel>,
    private  val itemAdapterCallback: ItemAdapterCallback,
): RecyclerView.Adapter<ProfileMenuAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):ViewHolder {
        val binding = ItemMenuProfileBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
       return listData.size
    }

    class ViewHolder (val binding:ItemMenuProfileBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind (data: ProfileMenuModel,itemAdapterCallback: ItemAdapterCallback) {
            with(binding) {
                tvTitle.text = data.title
                binding.root.setOnClickListener{itemAdapterCallback.onClick(it, data)}

            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data:ProfileMenuModel)

    }
}