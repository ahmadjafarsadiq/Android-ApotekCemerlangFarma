package com.jafarcode.apotekcemerlangfarma.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.jafarcode.apotekcemerlangfarma.R
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentDetailBinding
import com.jafarcode.apotekcemerlangfarma.model.response.home.Data
import com.jafarcode.apotekcemerlangfarma.utils.Helpers.formatPrice

class DetailFragment : Fragment() {


    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    var bundle:Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            DetailFragmentArgs.fromBundle(it).data?.let {
                initView(it)
            }
        }


        binding.btnOrderNow.setOnClickListener{

            Navigation.findNavController(it).navigate(R.id.action_payment, bundle)
            (activity as DetailActivity).toolbarPayment()
        }

    }


    private fun initView(data: Data?) {

        data?.let {

            bundle = bundleOf("data" to data)

            Glide.with(requireContext())
                .load(it.picturePath)
                .into(binding.ivPoster)

            binding.tvTitle.text = it.name
            binding.tvDesc.text = it.description
            binding.tvIngredients.text = it.ingredients
            binding.tvDose.text = it.dose
            binding.tvUse.text = it.howToUse
            binding.tvEffect.text = it.effect

            binding.tvTotal.formatPrice(it.price.toString())
        }

    }
    

}