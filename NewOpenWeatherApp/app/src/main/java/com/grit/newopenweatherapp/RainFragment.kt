package com.grit.newopenweatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.grit.newopenweatherapp.databinding.FragmentHomeBinding
import com.grit.newopenweatherapp.databinding.FragmentRainBinding
import com.grit.newopenweatherapp.databinding.FragmentRegisterBinding
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class RainFragment : Fragment() {

    private lateinit var binding: FragmentRainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRainBinding.inflate(inflater, container, false)

      //  var weather = binding.rainText.text

        callApi()

        binding.btnBackToHome.setOnClickListener {
            (activity as SecondActivity).replaceFragment(HomeFragment())
        }

        return binding.root
    }

    internal fun callApi() {

        val city = "Malmo"
        val countryCode = "SE"
        var key = "130541cf0c72d66e30719ae7d981f794"
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$city,$countryCode&appid=$key"

        val queue = Volley.newRequestQueue(context)

        Log.d("API", "call API")

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                setValues(response)
                Log.d("API", "Get values")
            },
            {
                Log.d("API", "Error")
            }
        )
        queue.add(jsonRequest)
    }

    private fun setValues(response: JSONObject) {

        var weather = response.getJSONArray("weather").getJSONObject(0).getString("main")

        Log.d("API", weather.toString())

        if (weather == "Rain"){
            binding.rainText.text = "Yes"

        } else binding.rainText.text = "No"
    }

}