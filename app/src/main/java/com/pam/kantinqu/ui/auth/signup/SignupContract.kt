package com.pam.kantinqu.ui.auth.signup

import android.net.Uri
import com.pam.kantinqu.base.BasePresenter
import com.pam.kantinqu.base.BaseView
import com.pam.kantinqu.model.request.RegisterRequest
import com.pam.kantinqu.model.response.login.LoginResponse

interface SignupContract {

    interface  View: BaseView {
        fun onRegisterSuccess(loginResponse: LoginResponse, view: android.view.View)
        fun onRegisterPhotoSuccess(view: android.view.View)
        fun onRegisterFailed(message: String)
    }

    interface Presenter : SignupContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view: android.view.View)
        fun submitPhotoRegister(filePath: Uri, view: android.view.View)
    }
}