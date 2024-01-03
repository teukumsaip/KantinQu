package com.aas.foodmarketkotlin.ui.auth.signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.Navigation
import com.aas.foodmarketkotlin.R
import com.aas.foodmarketkotlin.model.request.RegisterRequest
import com.aas.foodmarketkotlin.ui.auth.AuthActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker

class SignupFragment : Fragment() {

    private var filePath: Uri? = null
    private lateinit var ivProfil: ImageView
    private lateinit var etFullname: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Inisialisasi UI elemen setelah layout inflates
        ivProfil = view?.findViewById(R.id.ivProfil) ?: return
        etFullname = view?.findViewById(R.id.etFullname) ?: return
        etEmail = view?.findViewById(R.id.etEmail) ?: return
        etPassword = view?.findViewById(R.id.etPassword) ?: return

        initDummy()
        initListener()

        val btnContinue: Button = view?.findViewById(R.id.btnContinue) ?: return

        btnContinue.setOnClickListener {

            var fullname = etFullname.text.toString()
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()

            if (fullname.isNullOrEmpty()) {
                etFullname.error = "Silahkan masukkan fullname"
                etFullname.requestFocus()
            } else if (email.isNullOrEmpty()) {
                etEmail.error = "Silahkan masukkan email"
                etEmail.requestFocus()
            } else if (password.isNullOrEmpty()) {
                etPassword.error = "Silahkan masukkan password"
                etPassword.requestFocus()
            } else {
                var data = RegisterRequest(
                    fullname, email, password, password, "", "", "", "", filePath
                )

                var bundle = Bundle()
                bundle.putParcelable("data",data)

                Navigation.findNavController(it)
                    .navigate(R.id.action_signup_address, bundle)

                val authActivity = activity as? AuthActivity
                authActivity?.let {
                    it.toolbarSignUpAddress(it.findViewById(R.id.toolbar))
                }
            }

        }
    }

    private fun initListener() {
        ivProfil.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .start()
        }
    }

    private fun initDummy() {
        etFullname.setText("Jennie Kim White")
        etEmail.setText("jennie.kim@blackpink.com")
        etPassword.setText("12345678")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            filePath = data?.data

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfil)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_LONG).show()
        }
    }
}
