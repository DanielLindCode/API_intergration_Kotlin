package com.grit.newopenweatherapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.grit.newopenweatherapp.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.sendToLogin.setOnClickListener {
            (activity as MainActivity).replaceFragment(LoginFragment())
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val pass = binding.inputPassword.text.toString()
            val confirmPass = binding.inputConfirmPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {

                            Toast.makeText(activity, "Account created!", Toast.LENGTH_LONG).show()
                            (activity as MainActivity).replaceFragment(LoginFragment())

                        } else
                            Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_LONG)
                                .show()
                    }

                } else {
                    Toast.makeText(activity, "Password is not matching", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(activity, "Please fill in the missing fields", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return binding.root

    }

}
