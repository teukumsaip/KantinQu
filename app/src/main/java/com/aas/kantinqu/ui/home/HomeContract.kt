package com.aas.kantinqu.ui.home

import com.aas.kantinqu.base.BasePresenter
import com.aas.kantinqu.base.BaseView
import com.aas.kantinqu.model.response.home.HomeResponse

interface HomeContract {

    interface  View: BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message: String)
    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
    }
}