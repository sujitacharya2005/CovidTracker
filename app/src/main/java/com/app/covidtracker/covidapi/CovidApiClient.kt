package com.example.myapplication.covidapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CovidApiClient {
    companion object{
        private const val BASE_URL = "https://api.covid19api.com/"
        fun create() : CovidApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CovidApiService::class.java)
        }
    }
}