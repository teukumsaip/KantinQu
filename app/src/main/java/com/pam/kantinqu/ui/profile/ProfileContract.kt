package com.pam.kantinqu.ui.profile

import com.pam.kantinqu.base.BasePresenter
import com.pam.kantinqu.base.BaseView
import com.pam.kantinqu.model.response.ProfileResponse

interface ProfileContract {
    interface View : BaseView {
        fun onProfileSuccess(profileResponse: ProfileResponse)
        fun onProfileFailed(message: String)
    }

    interface Presenter : ProfileContract, BasePresenter {
        fun getProfile()
    }
}