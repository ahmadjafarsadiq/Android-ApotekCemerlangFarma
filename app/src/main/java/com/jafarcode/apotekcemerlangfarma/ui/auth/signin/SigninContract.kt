package com.jafarcode.apotekcemerlangfarma.ui.auth.signin

import com.jafarcode.apotekcemerlangfarma.base.BasePresenter
import com.jafarcode.apotekcemerlangfarma.base.BaseView
import com.jafarcode.apotekcemerlangfarma.model.response.login.LoginResponse

interface SigninContract {

    interface View: BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message:String)

    }

    interface Presenter:SigninContract, BasePresenter {
        fun submitLogin(email:String, password:String)

    }
}