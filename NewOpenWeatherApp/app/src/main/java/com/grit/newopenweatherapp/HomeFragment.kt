package com.grit.newopenweatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grit.newopenweatherapp.databinding.FragmentHomeBinding
import com.grit.newopenweatherapp.databinding.FragmentRegisterBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.btnRaining.setOnClickListener {
            (activity as SecondActivity).replaceFragment(RainFragment())
        }

        return binding.root
    }

}