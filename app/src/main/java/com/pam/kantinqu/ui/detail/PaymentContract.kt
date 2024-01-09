package com.pam.kantinqu.ui.detail

import com.pam.kantinqu.base.BasePresenter
import com.pam.kantinqu.base.BaseView
import com.pam.kantinqu.model.response.checkout.CheckoutResponse

interface PaymentContract {

    interface  View: BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message: String)
    }

    interface Presenter : PaymentContract, BasePresenter {
        fun getCheckout(foodId: String, userId:String, quantity:String, total: String,view: android.view.View)
    }
}