package com.jafarcode.apotekcemerlangfarma.ui.order.detailorders

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.jafarcode.apotekcemerlangfarma.R
import com.jafarcode.apotekcemerlangfarma.databinding.ActivityOrderdetailBinding

class OrderDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderdetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderdetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        toolbarDetail()

       intent.extras?.let {
            val navController = Navigation.findNavController(findViewById(R.id.detailOrdersHostFragment))
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable?)
            /*navController.setGraph(navController.graph, bundle)*/
            navController.setGraph(R.navigation.nav_detail_orders, bundle)
        }
    }

/*
     fun toolbarPayment() {
        binding.includeToolbar.apply {
            toolbar.visibility = View.VISIBLE
            toolbar.title = "Pembayaran"
            toolbar.subtitle = "Segera Melakukan Pembayaran"
            toolbar.navigationIcon = ContextCompat.getDrawable(this@OrderDetailActivity, R.drawable.arrow_back_ios_24px)
            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        }
    }
*/

    fun toolbarDetail() {
        binding.includeToolbar.apply {
            toolbar.visibility = View.GONE
        }
    }

}