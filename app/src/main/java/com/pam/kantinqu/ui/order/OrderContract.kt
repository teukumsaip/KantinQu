package com.pam.kantinqu.ui.order

import com.pam.kantinqu.base.BasePresenter
import com.pam.kantinqu.base.BaseView
import com.pam.kantinqu.model.response.transaction.TransactionResponse

interface OrderContract {

    interface  View: BaseView {
        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message: String)
    }

    interface Presenter : OrderContract, BasePresenter {
        fun getTransaction()
    }
}