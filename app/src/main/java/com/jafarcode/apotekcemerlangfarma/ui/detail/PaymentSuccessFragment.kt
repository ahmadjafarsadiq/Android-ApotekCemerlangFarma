package com.jafarcode.apotekcemerlangfarma.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentPaymentSuccessBinding
import com.jafarcode.apotekcemerlangfarma.model.response.transaction.Data


class PaymentSuccessFragment : Fragment() {

    private var _binding : FragmentPaymentSuccessBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentPaymentSuccessBinding.inflate(inflater, container, false)
        val view = binding.root
        return (view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val btnMyOrder = binding.btnMyorder
*/
        (activity as DetailActivity).toolbarDetail()
        var data = arguments?.getParcelable<Data>("data")


        binding.btnOrderagain.setOnClickListener {
            requireActivity().finish()
        }

/*        binding.btnMyorder.setOnClickListener {
            val home = Intent(activity, OrderDetailActivity::class.java)
            startActivity(home)
          *//*  activity?.finishAffinity()*//*
            }*/

/*        binding.btnMyorder.setOnClickListener(View.OnClickListener () {
            val home = Intent(activity, OrderDetailActivity::class.java).putExtra("data", data)
            startActivity(home);
        })*/

        }
    }
