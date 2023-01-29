package com.jafarcode.apotekcemerlangfarma.ui.detail

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.jafarcode.apotekcemerlangfarma.ApotekCemerlangFarma
import com.jafarcode.apotekcemerlangfarma.R
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentPaymentBinding
import com.jafarcode.apotekcemerlangfarma.model.response.checkout.CheckoutResponse
import com.jafarcode.apotekcemerlangfarma.model.response.home.Data
import com.jafarcode.apotekcemerlangfarma.model.response.login.User
import com.jafarcode.apotekcemerlangfarma.utils.Helpers.formatPrice

class PaymentFragment : Fragment(), PaymentContract.View {

    private var _binding : FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    var progressDialog : Dialog? = null
    lateinit var presenter: PaymentPresenter

     var total:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data = arguments?.getParcelable<Data>("data")
        initView(data)

        initView()
        presenter = PaymentPresenter(this)

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
        var user = ApotekCemerlangFarma.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)
        var CheckoutResponse = Gson().fromJson(user, CheckoutResponse::class.java)
       binding.apply {
           tvTitle.text = data?.name
           tvPrice.formatPrice(data?.price.toString())

           /*tvAddress.text = CheckoutResponse?.quantity.toString()*/

           Glide.with(requireContext())
               .load(data?.picturePath)
               .into(ivPoster)

           tvNameItem.text = data?.name
           tvHarga.formatPrice(data?.price.toString())

           if (!data?.price.toString().isNullOrEmpty()) {
               var totalTax = data?.price?.div(11)
               tvTax.formatPrice(totalTax.toString())

               total = data?.price!!+totalTax!!
               tvTotal.formatPrice(total.toString())
           } else {
               tvPrice.text = "Rp 0"
               /*tvTax.text = "IDR 0"*/
               tvTotal.text = "Rp 0"
               total = 0
           }

           tvName.text = userResponse?.name
           tvPhoneNo.text = userResponse?.phoneNumber
           tvAddress.text = userResponse?.address
           tvCity.text = userResponse?.city


       }
        binding.btnCheckout.setOnClickListener {
            presenter.getCheckout(
                data?.id.toString(),
                userResponse?.id.toString(),
                "1",
                total.toString(),
                it
            )
               /*Navigation.findNavController(it).navigate(R.id.action_payment_success)*/
        }

    }

    override fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View) {

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(checkoutResponse.paymentUrl)
        startActivity(i)

        Navigation.findNavController(view).navigate(R.id.action_payment_success)
    }

    override fun onCheckoutFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}