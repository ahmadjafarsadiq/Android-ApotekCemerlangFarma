package com.jafarcode.apotekcemerlangfarma.ui.profile.whatsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jafarcode.apotekcemerlangfarma.databinding.FragmentProfileWhatsappBinding

class ProfileWhatsappFragment : Fragment() {

    private var _binding :FragmentProfileWhatsappBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileWhatsappBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnWhatsapp.setOnClickListener {
            val webIntent: Intent = Uri.parse("https://api.whatsapp.com/send?phone=6282110253330").let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(webIntent)
        }
    }

}