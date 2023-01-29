package com.jafarcode.apotekcemerlangfarma.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.jafarcode.apotekcemerlangfarma.ApotekCemerlangFarma
import com.jafarcode.apotekcemerlangfarma.MainActivity
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentSignupSuccessBinding
import com.jafarcode.apotekcemerlangfarma.model.response.login.LoginResponse


class SignupSuccessFragment : Fragment() , SignupContract.View {

    private var _binding : FragmentSignupSuccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignupSuccessBinding.inflate(inflater,container, false)
        val view = binding.root
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnFind.setOnClickListener {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finishAffinity()
        }

    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        ApotekCemerlangFarma.getApp().setToken(loginResponse.access_token)

        val gson= Gson()
        val json = gson.toJson(loginResponse.user)
        ApotekCemerlangFarma.getApp().setUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onRegisterPhotoSuccess(view: View) {
        TODO("Not yet implemented")
    }

    override fun onRegisterFailed(message: String) {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }
}