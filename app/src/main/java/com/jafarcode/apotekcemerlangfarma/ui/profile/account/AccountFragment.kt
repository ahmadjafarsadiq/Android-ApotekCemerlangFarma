package com.jafarcode.apotekcemerlangfarma.ui.profile.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.jafarcode.apotekcemerlangfarma.ApotekCemerlangFarma
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentAccountBinding
import com.jafarcode.apotekcemerlangfarma.model.response.login.User

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
            _binding = FragmentAccountBinding.inflate(inflater, container,false)
            return (binding.root)
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var user = ApotekCemerlangFarma.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        binding.apply{
            etName.setText(userResponse.name)
            etPhoneNumber.setText(userResponse.phoneNumber)
            etAddress.setText(userResponse.houseNumber.toString())
            etCity.setText(userResponse.city)

            if (!userResponse.profilePhotoUrl.isNullOrEmpty()) {
                Glide.with(requireActivity())
                    .load(userResponse.profilePhotoUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(binding.ivProfile)
            }

        }


    }
}