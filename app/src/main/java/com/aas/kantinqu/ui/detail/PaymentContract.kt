package com.aas.kantinqu.ui.detail

import com.aas.kantinqu.base.BasePresenter
import com.aas.kantinqu.base.BaseView
import com.aas.kantinqu.model.response.checkout.CheckoutResponse

interface PaymentContract {

    interface  View: BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message: String)
    }

    interface Presenter : PaymentContract, BasePresenter {
        fun getCheckout(foodId: String, userId:String, quantity:String, total: String,view: android.view.View)
    }
}