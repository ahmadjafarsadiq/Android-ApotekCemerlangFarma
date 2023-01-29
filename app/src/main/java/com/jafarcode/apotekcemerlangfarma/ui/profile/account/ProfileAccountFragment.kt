package com.jafarcode.apotekcemerlangfarma.ui.profile.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentProfileAccountBinding
import com.jafarcode.apotekcemerlangfarma.model.dummy.ProfileMenuModel
import com.jafarcode.apotekcemerlangfarma.ui.profile.ProfileMenuAdapter

class ProfileAccountFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback {

    private var _binding : FragmentProfileAccountBinding? = null
    private val binding get() = _binding!!
    private var menuArrayList : ArrayList<ProfileMenuModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileAccountBinding.inflate(inflater, container,false)
        return (binding.root)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDatDummy()
        var adapter = ProfileMenuAdapter(menuArrayList,this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = adapter
/*
        binding.btnWhatsapp.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.account_profile)
        }*/

    }
    fun  initDatDummy() {
        menuArrayList = ArrayList()
        menuArrayList.add(ProfileMenuModel("Profil"))
        menuArrayList.add(ProfileMenuModel("Daftar Alamat"))
        menuArrayList.add(ProfileMenuModel("Kamanan Akun"))
        /*menuArrayList.add(ProfileMenuModel("Log Out"))*/

    }





    override fun onClick(v: View, data: ProfileMenuModel) {
        Toast.makeText(context, "Ini menu yang kamu klik"+data.title, Toast.LENGTH_SHORT).show()
    }

}

