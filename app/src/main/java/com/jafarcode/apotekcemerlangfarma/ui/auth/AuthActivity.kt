package com.jafarcode.apotekcemerlangfarma.ui.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.jafarcode.apotekcemerlangfarma.R
import com.jafarcode.apotekcemerlangfarma.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    lateinit var binding : ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pageRequest = intent.getIntExtra("page_request", 0)
        if (pageRequest == 2) {
            toolbarSignUp()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentSignIn, true)
                .build()
            Navigation.findNavController(findViewById(R.id.authHostFragment))
                .navigate(R.id.action_signup, null, navOptions)

        }
    }

    fun toolbarSignUp() {
         binding.includeToolbar.apply {
            toolbar.title = "Daftar"
             toolbar.subtitle="isi data dengan benar"
             toolbar.navigationIcon = ContextCompat.getDrawable(this@AuthActivity,R.drawable.arrow_back_ios_24px )
             toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
         }
     }

    fun toolbarSignUpAddress () {
        binding.includeToolbar.apply {
            toolbar.title = "Alamat"
            toolbar.subtitle = "isi data dengan benar"
            toolbar.navigationIcon =
                ContextCompat.getDrawable(this@AuthActivity, R.drawable.arrow_back_ios_24px)
            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        }
    }

    fun toolbarSignUpSuccess() {
            binding.includeToolbar.toolbar.visibility = View.GONE
        }

}