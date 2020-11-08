package com.app.covidtracker.detailscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.app.covidtracker.R
import com.app.covidtracker.constant.Constants
import com.app.covidtracker.covidapi.response.CovidSatus
import com.example.myapplication.covidapi.CovidApiClient
import com.example.myapplication.covidapi.response.Country
import kotlinx.android.synthetic.main.fragment_counry_status.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CounryStatusFragment : Fragment() {
    private var slug: String? = null
    lateinit var viewModel:DetailsViewModel
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

        val view = inflater.inflate(R.layout.fragment_counry_status, container, false)
        getStatusDetailsApi()
        return view
    }


    private fun getStatusDetailsApi() {
        slug?.let {
            CovidApiClient.create().getStatus(it).enqueue(object : Callback<List<CovidSatus>> {
                override fun onFailure(call: Call<List<CovidSatus>>, t: Throwable) {
                    Log.d("retrolog", "onResponse: "+t.message)
                }

                override fun onResponse(
                    call: Call<List<CovidSatus>>,
                    response: Response<List<CovidSatus>>
                ) {
                    if(response.body()!!.size > 0) {
                        Log.d(
                            "retrolog", "onResponse: "
                                    + (response.body() as List<CovidSatus>)[(response.body())!!.size - 1]
                        )
                        val status =
                            (response.body() as List<CovidSatus>)[(response.body())!!.size - 1]
                     viewModel.activeCasesLiveData.value = status.Active
                     viewModel.deathsCasesLiveData.value = status.Death
                     viewModel.confirmedCasesLiveData.value = status.Confirmed

                    }
                }

            })
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[DetailsViewModel::class.java]
        viewModel.activeCasesLiveData.observe(viewLifecycleOwner, Observer<Int>{
                t -> active_count.text = t.toString()
        })
        viewModel.deathsCasesLiveData.observe(viewLifecycleOwner, Observer<Int>{
                t -> confirmed_count.text = t.toString()
        })
        viewModel.confirmedCasesLiveData.observe(viewLifecycleOwner, Observer<Int>{
                t -> confirmed_count.text = t.toString()
        })
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            CounryStatusFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.SLUG, param1)
                }
            }
    }
}