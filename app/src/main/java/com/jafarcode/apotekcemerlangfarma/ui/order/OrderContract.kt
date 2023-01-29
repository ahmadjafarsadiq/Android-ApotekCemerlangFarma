package com.jafarcode.apotekcemerlangfarma.ui.order

import com.jafarcode.apotekcemerlangfarma.base.BasePresenter
import com.jafarcode.apotekcemerlangfarma.base.BaseView
import com.jafarcode.apotekcemerlangfarma.model.response.transaction.TransactionResponse

interface OrderContract {

    interface View: BaseView {
        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message:String)

    }

    interface Presenter:OrderContract, BasePresenter {
        fun getTransaction()

    }
}