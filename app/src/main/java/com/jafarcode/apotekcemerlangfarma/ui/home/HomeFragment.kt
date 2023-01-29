package com.jafarcode.apotekcemerlangfarma.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.jafarcode.apotekcemerlangfarma.ApotekCemerlangFarma
import com.jafarcode.apotekcemerlangfarma.R
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentHomeBinding
import com.jafarcode.apotekcemerlangfarma.model.response.home.Data
import com.jafarcode.apotekcemerlangfarma.model.response.home.HomeResponse
import com.jafarcode.apotekcemerlangfarma.model.response.login.User
import com.jafarcode.apotekcemerlangfarma.ui.detail.DetailActivity

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback,HomeContract.View {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var allList : ArrayList<Data> = ArrayList()
    private var vitaminList : ArrayList<Data> = ArrayList()
    private var covidList : ArrayList<Data> = ArrayList()// slide Horizontal

    private lateinit var presenter: HomePresenter
    var progressDialog : Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return (view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // function slide horizonral diambil dari Home Adapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        presenter = HomePresenter(this)
        presenter.getHome()

//        initDataDummy()

    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        var user = ApotekCemerlangFarma.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        if (!userResponse.profilePhotoUrl.isNullOrEmpty()) {
            Glide.with(requireActivity())
                .load(userResponse.profilePhotoUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivProfile)
        }


    }

/*    fun  initDataDummy() {
        drugList = ArrayList()
        drugList.add(HomeModel("Paracetamol", "", 5f))
        drugList.add(HomeModel("Nelco", "", 5f))
    }*/

    // pada saat diclick maka tidak akan error (test click)
    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
        }

    override fun onHomeSuccess(homeResponse: HomeResponse) {

        allList.clear()
        vitaminList.clear()
        covidList.clear()

        for (a in homeResponse.data.indices) {
            var items:List<String> = homeResponse.data[a].types?.split(", "  , ",") ?: ArrayList()
            for ( x in items.indices) {
                if (items[x].equals("anak" , true)) {
                    allList?.add(homeResponse.data[a])
                } else if (items[x].equals("dewasa" , true)) {
                    vitaminList?.add(homeResponse.data[a])
                } else if (items[x].equals("lansia" , true)) {
                    covidList?.add(homeResponse.data[a])
                }
            }
        }

        var adapter = HomeAdapter(homeResponse.data, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = adapter

        val sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        sectionPagerAdapter.setData(allList, vitaminList, covidList)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}