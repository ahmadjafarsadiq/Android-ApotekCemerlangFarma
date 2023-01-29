package com.jafarcode.apotekcemerlangfarma.ui.detail

import com.jafarcode.apotekcemerlangfarma.base.BasePresenter
import com.jafarcode.apotekcemerlangfarma.base.BaseView
import com.jafarcode.apotekcemerlangfarma.model.response.checkout.CheckoutResponse

interface PaymentContract {

    interface View: BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message:String)

    }

    interface Presenter:PaymentContract, BasePresenter {
        fun getCheckout(drugId : String, userId:String, quantity:String, total:String, view: android.view.View)
    }
}