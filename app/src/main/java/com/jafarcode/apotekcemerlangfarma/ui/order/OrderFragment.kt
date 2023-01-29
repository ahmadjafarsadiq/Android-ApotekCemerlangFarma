package com.jafarcode.apotekcemerlangfarma.ui.order

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jafarcode.apotekcemerlangfarma.ApotekCemerlangFarma
import com.jafarcode.apotekcemerlangfarma.R
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentOrderBinding
import com.jafarcode.apotekcemerlangfarma.model.response.transaction.Data
import com.jafarcode.apotekcemerlangfarma.model.response.transaction.TransactionResponse

class OrderFragment : Fragment(), OrderContract.View {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    lateinit var presenter: OrderPresenter
    var progressDialog: Dialog? = null

    var inprogressList:ArrayList<Data>? = ArrayList()
    var pastordersList:ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.onResume()
        initView()
        presenter = OrderPresenter(this)
        presenter.getTransaction()

    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
        binding.includeToolbar.toolbar.title = "Pesanan"
        binding.includeToolbar.toolbar.subtitle = "Tunggu pesanan kamu"
    }

    override fun onTransactionSuccess(transactionResponse: TransactionResponse) {

        Log.v("tamvan", "ini token "+ApotekCemerlangFarma.getApp().getToken())

        if (transactionResponse.data.isNullOrEmpty()) {
            binding.llEmpty.visibility = View.VISIBLE
            binding.llTab.visibility = View.GONE
            binding.includeToolbar
        } else  {

            for (a in transactionResponse.data.indices) {
                if (transactionResponse.data[a].status.equals("ON_DELIVERY", true) ||
                    transactionResponse.data[a].status.equals("PENDING", true)
                    ||transactionResponse.data[a].status.equals("PROCESS", true))
                {
                    inprogressList?.add(transactionResponse.data[a])
                }

                else if
                    (transactionResponse.data[a].status.equals("CANCELLED", true)
                    || transactionResponse.data[a].status.equals("DELIVERED", true)
                    || transactionResponse.data[a].status.equals("SUCCESS", true))
                {
                    pastordersList?.add(transactionResponse.data[a])
                }
            }

            val sectionPagerAdapter = SectionPagerAdapter(
                childFragmentManager
            )

            sectionPagerAdapter.setData(inprogressList, pastordersList)
            binding.viewPager.adapter = sectionPagerAdapter
            binding.tabLayout.setupWithViewPager(binding.viewPager)
        }
    }

    override fun onTransactionFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }


}