package com.jafarcode.apotekcemerlangfarma.ui.home

import com.jafarcode.apotekcemerlangfarma.base.BasePresenter
import com.jafarcode.apotekcemerlangfarma.base.BaseView
import com.jafarcode.apotekcemerlangfarma.model.response.home.HomeResponse

interface HomeContract {

    interface View: BaseView {
        fun onHomeSuccess(homeResponse:HomeResponse)
        fun onHomeFailed(message:String)

    }

    interface Presenter:HomeContract, BasePresenter {
        fun getHome()

    }
}