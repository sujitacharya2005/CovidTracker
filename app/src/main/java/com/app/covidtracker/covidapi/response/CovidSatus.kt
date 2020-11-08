package com.app.covidtracker.covidapi.response

import com.google.gson.annotations.SerializedName

data class CovidSatus (
    @SerializedName("Active")
    val Active:Int,
    @SerializedName("Deaths")
    val Death:Int,
    @SerializedName("Confirmed")
    val Confirmed:Int
)
