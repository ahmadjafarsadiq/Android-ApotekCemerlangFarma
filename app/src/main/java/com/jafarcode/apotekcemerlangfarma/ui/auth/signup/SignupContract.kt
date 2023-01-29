package com.jafarcode.apotekcemerlangfarma.ui.auth.signup

import android.net.Uri
import com.jafarcode.apotekcemerlangfarma.base.BasePresenter
import com.jafarcode.apotekcemerlangfarma.base.BaseView
import com.jafarcode.apotekcemerlangfarma.model.request.RegisterRequest
import com.jafarcode.apotekcemerlangfarma.model.response.login.LoginResponse

interface SignupContract {

    interface View: BaseView {
        fun onRegisterSuccess(loginResponse: LoginResponse, view: android.view.View)
        fun onRegisterPhotoSuccess(view: android.view.View)
        fun onRegisterFailed(message:String)
    }

    interface Presenter:SignupContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view: android.view.View)
        fun submitPhotoRegister(filePath:Uri, view: android.view.View)
    }
}