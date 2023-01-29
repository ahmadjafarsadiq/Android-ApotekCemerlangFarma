package com.jafarcode.apotekcemerlangfarma.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.jafarcode.apotekcemerlangfarma.ApotekCemerlangFarma
import com.jafarcode.apotekcemerlangfarma.MainActivity
import com.jafarcode.apotekcemerlangfarma.R
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentSigninBinding
import com.jafarcode.apotekcemerlangfarma.model.response.login.LoginResponse
import com.jafarcode.apotekcemerlangfarma.ui.auth.AuthActivity


class SigninFragment : Fragment(), SigninContract.View {

    private var _binding : FragmentSigninBinding? = null
    private val binding get() = _binding!!

    lateinit var presenter: SigninPresenter
    var progressDialog : Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = SigninPresenter(this)

//        if (!ApotekCemerlangFarma.getApp().getToken().isNullOrEmpty()) {
//            val home = Intent(activity, MainActivity::class.java)
//            startActivity(home)
//            activity?.finish()
//        }

        initDummy()
        initView()

        binding.btnSignup.setOnClickListener {
            val signup = Intent(activity, AuthActivity::class.java)
            signup.putExtra("page_request", 2)
            startActivity(signup)
        }

        binding.btnSignin.setOnClickListener {

            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()

            if (email.isNullOrEmpty()) {
                binding.etEmail.error = "Silakan Masukan Email Anda"
                binding.etEmail.requestFocus()
            } else if (password.isNullOrEmpty()) {
                binding.etPassword.error = "Silakan Masukan Password Anda"
                binding.etPassword.requestFocus()
            } else {
                presenter.submitLogin(email, password)
            }
        }
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        ApotekCemerlangFarma.getApp().setToken(loginResponse.access_token)

        val gson=Gson()
        val json = gson.toJson(loginResponse.user)
        ApotekCemerlangFarma.getApp().setUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        /*Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()*/
        Toast.makeText(activity, "tolong di periksa kembali ", Toast.LENGTH_SHORT).show()
    }

    private fun initDummy() {
        binding.etEmail.setText("")
        binding.etPassword.setText("")
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


    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }


}