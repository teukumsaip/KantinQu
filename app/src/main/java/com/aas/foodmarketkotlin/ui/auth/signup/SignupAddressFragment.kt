package com.aas.foodmarketkotlin.ui.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.aas.foodmarketkotlin.R
import com.aas.foodmarketkotlin.ui.auth.AuthActivity

class SignupAddressFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val btnSignUpNow: Button = view?.findViewById(R.id.btnSignUpNow) ?: return

        btnSignUpNow.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_signup_success, null)

            val authActivity = activity as? AuthActivity
            authActivity?.let {
                it.toolbarSignUpSuccess(it.findViewById(R.id.toolbar))
            }
        }
    }
}