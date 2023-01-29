package com.jafarcode.apotekcemerlangfarma.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.jafarcode.apotekcemerlangfarma.databinding.ActivitySplashScreenBinding
import com.jafarcode.apotekcemerlangfarma.ui.auth.AuthActivity

class SplashScreenActivity : AppCompatActivity() {

    lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

         Handler().postDelayed({
            startActivity(Intent(this, AuthActivity::class.java))
             finish()
        }, 3000)


    }

}