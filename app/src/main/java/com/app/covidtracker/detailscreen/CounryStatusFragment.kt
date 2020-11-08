package com.app.covidtracker.detailscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.covidtracker.R
import com.app.covidtracker.covidapi.response.CovidSatus
import com.example.myapplication.covidapi.CovidApiClient
import kotlinx.android.synthetic.main.fragment_counry_status.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CounryStatusFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_counry_status, container, false)
        getStatusDetailsApi()
        return view
    }
    private fun getStatusDetailsApi() {
        CovidApiClient.create().getStatus().enqueue(object : Callback<List<CovidSatus>> {
            override fun onFailure(call: Call<List<CovidSatus>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<CovidSatus>>,
                response: Response<List<CovidSatus>>
            ) {
                Log.d("retrolog", "onResponse: "
                        +(response.body() as List<CovidSatus>)[(response.body())!!.size-1])
                val status = (response.body() as List<CovidSatus>)[(response.body())!!.size-1]
                active_count.text = status.Active
                death_count.text = status.Death
                confirmed_count.text = status.Confirmed
            }

        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            CounryStatusFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}