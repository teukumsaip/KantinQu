package com.aas.kantinqu.ui.profile

import com.aas.kantinqu.base.BasePresenter
import com.aas.kantinqu.base.BaseView
import com.aas.kantinqu.model.response.ProfileResponse

interface ProfileContract {
    interface View : BaseView {
        fun onProfileSuccess(profileResponse: ProfileResponse)
        fun onProfileFailed(message: String)
    }

    interface Presenter : ProfileContract, BasePresenter {
        fun getProfile()
    }
}