package com.aas.foodmarketkotlin.ui.auth.signup

import android.app.Dialog
import android.os.Bundle
import android.provider.ContactsContract.RawContacts.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.aas.foodmarketkotlin.FoodMarket
import com.aas.foodmarketkotlin.R
import com.aas.foodmarketkotlin.model.request.RegisterRequest
import com.aas.foodmarketkotlin.model.response.login.LoginResponse
import com.aas.foodmarketkotlin.ui.auth.AuthActivity
import com.google.gson.Gson

class SignupAddressFragment : Fragment(), SignupContract.View {

    private lateinit var data: RegisterRequest
    lateinit var presenter: SignupPresenter
    var progressDialog:Dialog?=null

    private lateinit var etPhoneNumber: EditText
    private lateinit var etAddress: EditText
    private lateinit var etHouseNumber: EditText
    private lateinit var etCity: EditText
    private lateinit var btnSignUpNow: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_signup_address, container, false)

        btnSignUpNow = view.findViewById(R.id.btnSignUpNow)
        etPhoneNumber = view.findViewById(R.id.etPhoneNumber)
        etAddress = view.findViewById(R.id.etAddress)
        etHouseNumber = view.findViewById(R.id.etHouseNumber)
        etCity = view.findViewById(R.id.etCity)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignupPresenter(this)
        data = arguments?.getParcelable<RegisterRequest>("data")!!
        initListener()
        initView()

    }
    private fun initListener() {

        btnSignUpNow.setOnClickListener {

            var phone = etPhoneNumber.text.toString()
            var address = etAddress.text.toString()
            var houseNumber = etHouseNumber.text.toString()
            var city = etCity.text.toString()

            data.let {
                it.phoneNumber = phone
                it.address = address
                it.houseNumber = houseNumber
                it.city = city
            }

            if (phone.isNullOrEmpty()) {
                etPhoneNumber.error = "Silahkan masukkan phone number"
                etPhoneNumber.requestFocus()
            } else if (address.isNullOrEmpty()) {
                etAddress.error = "Silahkan masukkan address"
                etAddress.requestFocus()
            } else if (houseNumber.isNullOrEmpty()) {
                etHouseNumber.error = "Silahkan masukkan house number"
                etHouseNumber.requestFocus()
            } else if (city.isNullOrEmpty()) {
                etCity.error = "Silahkan masukkan city"
                etCity.requestFocus()
            } else {
                presenter.submitRegister(data, it)
            }
        }
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

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        FoodMarket.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        if (data.filePath == null) {
            Navigation.findNavController(view)
                .navigate(R.id.action_signup_success, null)

            val authActivity = activity as? AuthActivity
            authActivity?.let {
                it.toolbarSignUpSuccess(it.findViewById(R.id.toolbar))
            }
        }else {
            presenter.submitPhotoRegister(data.filePath!!, view)

        }
    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_signup_success, null)

        val authActivity = activity as? AuthActivity
        authActivity?.let {
            it.toolbarSignUpSuccess(it.findViewById(R.id.toolbar))
        }
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}