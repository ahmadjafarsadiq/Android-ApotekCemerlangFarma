package com.jafarcode.apotekcemerlangfarma.ui.auth.signup

import android.app.Activity
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
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.jafarcode.apotekcemerlangfarma.R
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentSignupBinding
import com.jafarcode.apotekcemerlangfarma.model.request.RegisterRequest


class SignupFragment : Fragment() {

    private var _binding : FragmentSignupBinding? = null
    private val binding get() = _binding!!

    var filePath:Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDummy()
        initListener()


    }

    private fun initListener() {
        binding.ivProfile.setOnClickListener{
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        binding.btnContinue.setOnClickListener {

            var fullname = binding.etFullname.text.toString()
            var email   = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()

            if (fullname.isNullOrEmpty()) {
                binding.etFullname.error = "Silakan Masukan Nama Lengkap"
            } else if (email.isNullOrEmpty()) {
                binding.etEmail.error = "Silakan Masukan Email"
            } else if (password.isNullOrEmpty()) {
                binding.etPassword.error = "Silakan Masukan Password"
            } else {
                var data = RegisterRequest (
                    fullname,
                    email,
                    password,
                    password,
                    "", "", "", "",
                    filePath

                )

                var bundle = Bundle()
                bundle.putParcelable("data", data)

                Navigation.findNavController(it)
                    .navigate(R.id.action_signup_address, bundle)

                (activity as com.jafarcode.apotekcemerlangfarma.ui.auth.AuthActivity).toolbarSignUpAddress()

            }

        }
    }

    private fun initDummy(){
        binding.etFullname.setText("")
        binding.etEmail.setText("")
        binding.etPassword.setText("")
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            filePath = data?. data

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivProfile)
        }else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context,"Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }



}