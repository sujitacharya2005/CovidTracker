package com.example.myapplication.covidapi

import com.app.covidtracker.covidapi.response.CovidSatus
import com.app.covidtracker.covidapi.response.CovidSatusDateWise
import com.example.myapplication.covidapi.response.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CovidApiService {
    @GET("/countries")
    fun getCountries() : Call<List<Country>>

    @GET("/country/{country}")
    fun getStatus(@Path("country") country: String) : Call<List<CovidSatus>>

    @GET("/total/country/{country}/status/confirmed")
    fun getStatusDateWise(@Path("country") country: String) : Call<List<CovidSatusDateWise>>

}