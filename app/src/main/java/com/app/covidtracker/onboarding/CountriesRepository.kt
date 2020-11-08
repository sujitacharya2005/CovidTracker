package com.example.myapplication.onboarding

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.covidapi.CovidApiClient
import com.example.myapplication.covidapi.response.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



object CountriesRepository {
    fun getCountries(): MutableLiveData<List<Country>> {
        val countriesLiveData = MutableLiveData<List<Country>>()
        CovidApiClient.create().getCountries().enqueue(object : Callback<List<Country>> {
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                countriesLiveData.value = listOf(Country("error", "slug"))
                Log.d("failurelog", "onFailure: "+t.message)
            }

            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful) {
                    countriesLiveData.value = response.body()
                }
            }
        })
        return countriesLiveData
    }
}
