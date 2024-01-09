package com.pam.kantinqu.ui.auth.signin

import com.pam.kantinqu.base.BasePresenter
import com.pam.kantinqu.base.BaseView
import com.pam.kantinqu.model.response.login.LoginResponse

interface SigninContract {

    interface  View: BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: String)
    }

    interface Presenter : SigninContract, BasePresenter {
        fun submitLogin(email: String, password: String)
    }
}