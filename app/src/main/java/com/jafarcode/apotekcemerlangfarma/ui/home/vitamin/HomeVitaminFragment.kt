package com.jafarcode.apotekcemerlangfarma.ui.home.vitamin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentHomeAllBinding
import com.jafarcode.apotekcemerlangfarma.model.response.home.Data
import com.jafarcode.apotekcemerlangfarma.ui.detail.DetailActivity
import com.jafarcode.apotekcemerlangfarma.ui.home.all.HomeAllAdapter

class HomeVitaminFragment : Fragment(), HomeAllAdapter.ItemAdapterCallback {

    private var _binding : FragmentHomeAllBinding? = null
    private val binding get() = _binding!!

    /*private var drugList : ArrayList<HomeVerticalModel> = ArrayList()*/

    private var vitaminList :ArrayList<Data>? = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeAllBinding.inflate(inflater, container, false)
        val view = binding.root
        return (view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        vitaminList?.clear()
        vitaminList = arguments?.getParcelableArrayList("data")

//        initDataDummy()
        var adapter = HomeAllAdapter(vitaminList!!, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = adapter
    }

/*
    fun  initDataDummy() {
        drugList = ArrayList()
        drugList.add(HomeVerticalModel("Paracetamol", "20000", ""))
        drugList.add(HomeVerticalModel("Nelco", "10000", ""))
    }
*/

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }


}