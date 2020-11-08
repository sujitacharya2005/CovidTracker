package com.app.covidtracker.covidapi.response

import com.google.gson.annotations.SerializedName

data class CovidSatusDateWise (
    @SerializedName("Cases")
    val Cases:Int
    )