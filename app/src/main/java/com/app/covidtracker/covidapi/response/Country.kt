package com.example.myapplication.covidapi.response

import com.google.gson.annotations.SerializedName

data class Country (
    @SerializedName("Country")
    val name : String,

    @SerializedName("Slug")
    val slug : String
)