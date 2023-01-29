package com.jafarcode.apotekcemerlangfarma.ui.auth.signup

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.jafarcode.apotekcemerlangfarma.ApotekCemerlangFarma
import com.jafarcode.apotekcemerlangfarma.R
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentSignupAddressBinding
import com.jafarcode.apotekcemerlangfarma.model.request.RegisterRequest
import com.jafarcode.apotekcemerlangfarma.model.response.login.LoginResponse


class SignupAddressFragment : Fragment() , SignupContract.View{

    private var _binding : FragmentSignupAddressBinding? = null
    private val binding get() = _binding!!

    private lateinit var data:RegisterRequest
    lateinit var presenter: SignupPresenter
    var progressDialog : Dialog ? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignupAddressBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data = arguments?.getParcelable<RegisterRequest>("data")!!

        presenter = SignupPresenter(this)
        initDummy()
        initListener()
        initView()

    }

    private fun initListener() {

        binding.btnSignUpNow.setOnClickListener {

            var phone = binding.etPhoneNumber.text.toString()
            var address = binding.etAddress.text.toString()
            var houseNumber = binding.etHouseNumber.text.toString()
            var city = binding.etCity.text.toString()

            data.let {
                it.address = address
                it.phoneNumber = phone
                it.houseNumber = houseNumber
                it.city = city
            }

            if (phone.isNullOrEmpty()) {
                binding.etPhoneNumber.error = "Silakan masukan nomor telephone"
                binding.etPhoneNumber.requestFocus()
            } else if (address.isNullOrEmpty()) {
                binding.etAddress.error = "Silakan masukan alamat lengkap anda"
                binding.etAddress.requestFocus()
            } else if (houseNumber.isNullOrEmpty()) {
                binding.etHouseNumber.error = "Silakan masukan nomor rumah anda "
                binding.etHouseNumber.requestFocus()
            } else if (city.isNullOrEmpty()) {
                binding.etCity.error = "Silakan masukan kota anda"
                binding.etCity.requestFocus()
            } else {
                presenter.submitRegister(data, it)
            }
/*
            Navigation.findNavController(it)
                .navigate(R.id.action_signup_success, null)
            (activity as com.jafarcode.apotekcemerlangfarma.ui.auth.AuthActivity).toolbarSignUpSuccess()*/
        }
    }

    private fun initDummy(){
        binding.etPhoneNumber.setText("")
        binding.etAddress.setText("")
        binding.etHouseNumber.setText("")
        binding.etCity.setText("")
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        ApotekCemerlangFarma.getApp().setToken(loginResponse.access_token)

        val gson= Gson()
        val json = gson.toJson(loginResponse.user)
        ApotekCemerlangFarma.getApp().setUser(json)

        if (data.filePath == null){
            Navigation.findNavController(view)
                .navigate(R.id.action_signup_success, null)
            (activity as com.jafarcode.apotekcemerlangfarma.ui.auth.AuthActivity).toolbarSignUpSuccess()
        } else {
            presenter.submitPhotoRegister(data.filePath!!, view)
        }
    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_signup_success, null)
        (activity as com.jafarcode.apotekcemerlangfarma.ui.auth.AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterFailed(message: String) {
        /*Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()*/
        Toast.makeText(activity, "email sudah terdaftar", Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }


}