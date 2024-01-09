package com.aas.kantinqu.ui.order

import com.aas.kantinqu.base.BasePresenter
import com.aas.kantinqu.base.BaseView
import com.aas.kantinqu.model.response.transaction.TransactionResponse

interface OrderContract {

    interface  View: BaseView {
        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message: String)
    }

    interface Presenter : OrderContract, BasePresenter {
        fun getTransaction()
    }
}