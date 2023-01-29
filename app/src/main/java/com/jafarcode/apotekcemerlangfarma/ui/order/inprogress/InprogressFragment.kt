package com.jafarcode.apotekcemerlangfarma.ui.order.inprogress

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentInprogressBinding
import com.jafarcode.apotekcemerlangfarma.model.response.transaction.Data
import com.jafarcode.apotekcemerlangfarma.ui.order.detailorders.OrderDetailActivity

class InprogressFragment : Fragment(), InprogressAdapter.ItemAdapterCallback {

    private var _binding : FragmentInprogressBinding? = null
    private val binding get() = _binding!!

    private var adapter: InprogressAdapter?= null
    var inprogressList:ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInprogressBinding.inflate(inflater, container,false)
        return (binding.root)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inprogressList = arguments?.getParcelableArrayList("data")


        if (!inprogressList.isNullOrEmpty()) {
            adapter = InprogressAdapter(inprogressList!!, this)
            val layoutManager = LinearLayoutManager(activity)
            binding.rcList.layoutManager = layoutManager
            binding.rcList.adapter = adapter

        }
    }

/*    override fun onResume() {
        super.onResume()
        if (!inprogressList.isNullOrEmpty()) {
            adapter = InprogressAdapter(inprogressList!!, this)
            val layoutManager = LinearLayoutManager(activity)
            binding.rcList.layoutManager = layoutManager
            binding.rcList.adapter = adapter

        }
    }*/
    override fun onClick(v: View, data: Data) {
          val detail = Intent(activity, OrderDetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }


}

