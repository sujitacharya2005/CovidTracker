package com.app.covidtracker.detailscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.covidtracker.R
import com.app.covidtracker.constant.Constants
import com.app.covidtracker.covidapi.response.CovidSatus
import com.example.myapplication.covidapi.CovidApiClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DateWiseFragment : Fragment() {
    private var slug: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            slug = it.getString(Constants.SLUG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_date_wise, container, false)
        getStatusDetailsApi()
        return view
    }

    private fun getStatusDetailsApi() {
        CovidApiClient.create().getStatus().enqueue(object : Callback<List<CovidSatus>>{
            override fun onFailure(call: Call<List<CovidSatus>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<CovidSatus>>,
                response: Response<List<CovidSatus>>
            ) {
                Log.d("retrolog", "onResponse: "
                        +(response.body() as List<CovidSatus>)[(response.body())!!.size-1])
            }

        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            DateWiseFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.SLUG, param1)
                }
            }
    }
}