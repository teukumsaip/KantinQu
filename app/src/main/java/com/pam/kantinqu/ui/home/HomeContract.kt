package com.pam.kantinqu.ui.home

import com.pam.kantinqu.base.BasePresenter
import com.pam.kantinqu.base.BaseView
import com.pam.kantinqu.model.response.home.HomeResponse

interface HomeContract {

    interface  View: BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message: String)
    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
    }
}