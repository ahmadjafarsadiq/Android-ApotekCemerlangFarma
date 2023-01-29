package com.jafarcode.apotekcemerlangfarma.ui.order.pastorders

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentPastordersBinding
import com.jafarcode.apotekcemerlangfarma.model.response.transaction.Data
import com.jafarcode.apotekcemerlangfarma.ui.order.detailorders.OrderDetailActivity
import com.jafarcode.apotekcemerlangfarma.ui.order.inprogress.InprogressAdapter

class PastordersFragment : Fragment(), InprogressAdapter.ItemAdapterCallback{

    private var _binding : FragmentPastordersBinding? = null
    private val binding get() = _binding!!

    private var adapter: InprogressAdapter?= null
    var pastorderList:ArrayList<Data>? = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPastordersBinding.inflate(inflater, container,false)
        return (binding.root)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pastorderList = arguments?.getParcelableArrayList("data")


        if (!pastorderList.isNullOrEmpty()) {
            adapter = InprogressAdapter(pastorderList!!, this)
            val layoutManager = LinearLayoutManager(activity)
            binding.rcList.layoutManager = layoutManager
            binding.rcList.adapter = adapter

        }
    }


        override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, OrderDetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }


}

