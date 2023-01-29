package com.jafarcode.apotekcemerlangfarma.ui.order.detailorders

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jafarcode.apotekcemerlangfarma.R
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentOrderDetailBinding
import com.jafarcode.apotekcemerlangfarma.model.response.transaction.Data
import com.jafarcode.apotekcemerlangfarma.utils.Helpers.formatPrice

class OrderDetailFragment : Fragment(), OrderDetailContract.View {

    private var _binding : FragmentOrderDetailBinding? = null
    private val binding get() = _binding!!

    var progressDialog : Dialog? = null
    lateinit var presenter: OrderDetailPresenter

     var total:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderDetailBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var it = arguments?.getParcelable<Data>("data")

        /*(activity as OrderDetailActivity?)!!.toolbarPayment()*/
        initView(it)

        initView()
        presenter = OrderDetailPresenter(this)

    }

    private fun initView(){
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun initView(data: Data?) {

       binding.apply {
           tvTitle.text = data?.drug?.name
           tvPrice.formatPrice(data?.drug?.price.toString())

           Glide.with(requireContext())
               .load(data?.drug?.picturePath)
               .into(ivPoster)

           tvNameItem.text = data?.drug?.name
           tvHarga.formatPrice(data?.drug?.price.toString())

           if (!data?.drug?.price.toString().isNullOrEmpty()) {
               var totalTax = data?.drug?.price?.div(11)
               tvTax.formatPrice(totalTax.toString())

               total = data?.drug?.price!!+totalTax!!
               tvTotal.formatPrice(total.toString())
           } else {
               tvPrice.text = "IDR 0"
               /*tvTax.text = "IDR 0"*/
               tvTotal.text = "IDR 0"
               total = 0
           }

           tvName.text = data?.user?.name
           tvPhoneNo.text = data?.user?.phoneNumber
           tvAddress.text =data?.user?.address
           tvCity.text = data?.user?.city

          tvOrderStatus.text = data?.id.toString()

            if (data?.status.equals("ON_DELIVERY", true)) {
               btnCancelled.visibility = View.VISIBLE
               btnCancelled.text = "Selesai"
               binding.constraintlayout3.visibility = View.VISIBLE
               tvPending.text = "Sedang Dikirim"
           }  else if (data?.status.equals("SUCCESS", true)) {
               btnCancelled.visibility = View.INVISIBLE
               binding.constraintlayout3.visibility = View.VISIBLE
               tvPending.text = "Selesai"
           } else if (data?.status.equals("PROCESS", true)) {
               btnCancelled.visibility = View.VISIBLE
               binding.constraintlayout3.visibility = View.VISIBLE
               tvPending.text = "Lunas dan Sedang Diproses"
           } else if (data?.status.equals("CANCELLED", true)) {
               btnCancelled.visibility = View.INVISIBLE
               binding.constraintlayout3.visibility = View.VISIBLE
               tvPending.text = "Dibatalkan"
           } else if (data?.status.equals("PENDING", true)) {
               btnCancelled.visibility = View.VISIBLE
               btnCancelled.text = "Bayar Sekarang"
               binding.constraintlayout3.visibility = View.VISIBLE
               tvPending.text = "Menunggu Pembayaran"
           }
       }
/*
        binding.btnCancelled.setOnClickListener {
           if (binding.btnCancelled.text.equals("Selesai")) {
                presenter.getUpdateTransaction(data?.id.toString(), "SUCCESS")

            } else{
                presenter.getUpdateTransaction(data?.id.toString(), "CANCELLED")
            }
        }*/

        binding.btnCancelled.setOnClickListener {
            if (binding.btnCancelled.text.equals("Bayar Sekarang")) {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(data?.paymentUrl)
                startActivity(i)
            } else if (data?.status.equals("ON_DELIVERY")) {
                presenter.getUpdateTransaction(data?.id.toString(), "SUCCESS")
                Toast.makeText(context, "Pesanan Kamu Selesai", Toast.LENGTH_SHORT).show()
            }
            else{
                presenter.getUpdateTransaction(data?.id.toString(), "CANCELLED")
            }
        }
    }


    override fun onUpdateTransactionSuccess(message: String) {
       /* Toast.makeText(activity, message, Toast.LENGTH_LONG).show()*/
        requireActivity().finish()
    }

    override fun onUpdateTransactionFailed(message: String) {

        Toast.makeText(context, "Pesananmu dibatalkan", Toast.LENGTH_SHORT).show()

//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}