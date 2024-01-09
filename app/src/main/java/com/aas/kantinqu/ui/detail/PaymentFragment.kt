package com.aas.kantinqu.ui.detail

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.aas.kantinqu.FoodMarket
import com.aas.kantinqu.R
import com.aas.kantinqu.model.response.checkout.CheckoutResponse
import com.aas.kantinqu.model.response.home.Data
import com.aas.kantinqu.model.response.login.User
import com.aas.kantinqu.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import com.google.gson.Gson

class PaymentFragment : Fragment() , PaymentContract.View {


    var progressDialog: Dialog?= null
    lateinit var presenter: PaymentPresenter
    var total: Int = 0

    private lateinit var ivPoster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvNameItem: TextView
    private lateinit var tvHarga: TextView
    private lateinit var tvTax: TextView
    private lateinit var tvTotal: TextView
    private lateinit var tvName: TextView
    private lateinit var tvPhoneNo: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvCity: TextView
    private lateinit var tvHouse: TextView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivPoster = view.findViewById(R.id.ivPoster)
        tvTitle = view.findViewById(R.id.tvTitle)
        tvPrice = view.findViewById(R.id.tvPrice)
        tvNameItem = view.findViewById(R.id.tvNameItem)
        tvHarga = view.findViewById(R.id.tvHarga)
        tvTax = view.findViewById(R.id.tvTax)
        tvTotal = view.findViewById(R.id.tvTotal)
        tvName = view.findViewById(R.id.tvName)
        tvPhoneNo = view.findViewById(R.id.tvPhoneNo)
        tvAddress = view.findViewById(R.id.tvAddress)
        tvCity = view.findViewById(R.id.tvCity)
        tvHouse = view.findViewById(R.id.tvHouse)

        val data = arguments?.getParcelable<Data>("data")
        initView(data)
        initView()
        presenter = PaymentPresenter(this)
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun initView(data: Data?) {
        tvTitle.text = data?.name
        tvPrice.formatPrice(data?.price.toString())

        Glide.with(requireContext())
            .load(data?.picturePath)
            .into(ivPoster)

        tvNameItem.text = data?.name
        tvHarga.formatPrice(data?.price.toString())

        if (!data?.price.toString().isNullOrEmpty()) {
            val totalTax = data?.price?.div(10)
            tvTax.formatPrice(totalTax.toString())

            total = data?.price!! + totalTax!! + 10000
            tvTotal.formatPrice(total.toString())
        } else {
            tvPrice.text = "IDR 0"
            tvHarga.text = "IDR 0"
            tvTax.text = "IDR 0"
            tvTotal.text = "IDR 0"
            total = 0
        }

        var user = FoodMarket.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        tvName.text = userResponse?.name
        tvPhoneNo.text = userResponse?.phoneNumber
        tvAddress.text = userResponse?.address
        tvCity.text = userResponse?.city
        tvHouse.text = userResponse?.houseNumber

        val btnCheckOut: Button = requireView().findViewById(R.id.btnCheckout)

        btnCheckOut.setOnClickListener {
            presenter.getCheckout(
                data?.id.toString(),
                userResponse?.id.toString(),
                "1",
                total.toString(),
                it
            )
        }
    }

    override fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View) {

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(checkoutResponse.paymentUrl)
        startActivity(i)

        Navigation.findNavController(view).navigate(R.id.action_payment_success)
    }

    override fun onCheckoutFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}