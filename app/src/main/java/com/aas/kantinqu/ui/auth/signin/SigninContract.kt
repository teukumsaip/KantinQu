package com.aas.kantinqu.ui.auth.signin

import com.aas.kantinqu.base.BasePresenter
import com.aas.kantinqu.base.BaseView
import com.aas.kantinqu.model.response.login.LoginResponse

interface SigninContract {

    interface  View: BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: String)
    }

    interface Presenter : SigninContract, BasePresenter {
        fun submitLogin(email: String, password: String)
    }
}