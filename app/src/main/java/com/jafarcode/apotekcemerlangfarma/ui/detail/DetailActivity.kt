package com.jafarcode.apotekcemerlangfarma.ui.detail

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.jafarcode.apotekcemerlangfarma.R
import com.jafarcode.apotekcemerlangfarma.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        toolbarDetail()

       intent.extras?.let {
            val navController = Navigation.findNavController(findViewById(R.id.detailHostFragment))
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable?)
            /*navController.setGraph(navController.graph, bundle)*/
            navController.setGraph(R.navigation.nav_detail, bundle)
        }
    }

     fun toolbarPayment() {
        binding.includeToolbar.apply {
            toolbar.visibility = View.VISIBLE
            toolbar.title = "Pembayaran"
            toolbar.subtitle = "Segera Melakukan Pembayaran"
            toolbar.navigationIcon = ContextCompat.getDrawable(this@DetailActivity, R.drawable.arrow_back_ios_24px)
            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        }
    }

    fun toolbarDetail() {
        binding.includeToolbar.apply {
            toolbar.visibility = View.GONE
        }
    }

}