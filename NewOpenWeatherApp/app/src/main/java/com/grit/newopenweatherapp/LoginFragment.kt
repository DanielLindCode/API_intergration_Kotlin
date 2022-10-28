package com.grit.newopenweatherapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.grit.newopenweatherapp.databinding.FragmentLoginBinding
import com.grit.newopenweatherapp.databinding.FragmentRegisterBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.moveToRegister.setOnClickListener {
            (activity as MainActivity).replaceFragment(RegisterFragment())
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {

            val email = binding.loginInputEmail.text.toString()
            val pass = binding.loginInputPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(activity, SecondActivity::class.java)
                        startActivity(intent)

                    } else
                        Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_LONG)
                            .show()
                }
            } else {
                Toast.makeText(activity, "Please fill in the missing fields", Toast.LENGTH_LONG)
                    .show()
            }

        }

        return binding.root
    }
}