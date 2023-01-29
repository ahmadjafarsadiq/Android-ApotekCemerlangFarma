package com.jafarcode.apotekcemerlangfarma.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.jafarcode.apotekcemerlangfarma.ApotekCemerlangFarma
import com.jafarcode.apotekcemerlangfarma.R
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentProfileBinding
import com.jafarcode.apotekcemerlangfarma.model.response.login.User

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionPagerAdapter = SectionPagerAdapter (
            childFragmentManager  )

        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        var user = ApotekCemerlangFarma.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        binding.tvName.setText(userResponse.name)
        binding.tvEmail.setText(userResponse.email)


        if (!userResponse.profilePhotoUrl.isNullOrEmpty()) {
            Glide.with(requireActivity())
                .load(userResponse.profilePhotoUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivPoster)
        }

        binding.idEdit.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.account_profile)
        }

    }

}