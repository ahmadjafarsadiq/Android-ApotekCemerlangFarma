package com.jafarcode.apotekcemerlangfarma.ui.order.detailorders

import com.jafarcode.apotekcemerlangfarma.base.BasePresenter
import com.jafarcode.apotekcemerlangfarma.base.BaseView

interface OrderDetailContract {

    interface View: BaseView {
        fun onUpdateTransactionSuccess(message:String)
        fun onUpdateTransactionFailed(message:String)

    }

    interface Presenter:OrderDetailContract, BasePresenter {
        fun getUpdateTransaction(id : String, status : String)
    }
}