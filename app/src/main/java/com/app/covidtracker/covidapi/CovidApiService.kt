package com.example.myapplication.covidapi

import com.example.myapplication.covidapi.response.Country
import retrofit2.Call
import retrofit2.http.GET


interface CovidApiService {
    @GET("/countries")
    fun getCountries() : Call<List<Country>>
}