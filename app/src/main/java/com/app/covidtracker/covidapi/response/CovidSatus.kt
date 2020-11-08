package com.app.covidtracker.covidapi.response

import com.google.gson.annotations.SerializedName

data class CovidSatus (
    @SerializedName("Active")
    val Active:String,
    @SerializedName("Deaths")
    val Death:String,
    @SerializedName("Confirmed")
    val Confirmed:String
)
