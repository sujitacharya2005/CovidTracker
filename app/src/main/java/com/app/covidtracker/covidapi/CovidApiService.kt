package com.example.myapplication.covidapi

import com.app.covidtracker.covidapi.response.CovidSatus
import com.example.myapplication.covidapi.response.Country
import retrofit2.Call
import retrofit2.http.GET


interface CovidApiService {
    @GET("/countries")
    fun getCountries() : Call<List<Country>>

    @GET("/country/india")
    fun getStatus() : Call<List<CovidSatus>>
}