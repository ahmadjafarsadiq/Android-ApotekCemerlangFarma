package com.jafarcode.apotekcemerlangfarma.ui.profile.detailprofile

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.jafarcode.apotekcemerlangfarma.R
import com.jafarcode.apotekcemerlangfarma.databinding.ActivityDetailAccountBinding

class DetailAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        intent.extras?.let {
            val navController = Navigation.findNavController(findViewById(R.id.DetailAccountFragment))
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable?)
            /*navController.setGraph(navController.graph, bundle)*/
            navController.setGraph(R.navigation.nav_detail_account, bundle)
        }
    }
}