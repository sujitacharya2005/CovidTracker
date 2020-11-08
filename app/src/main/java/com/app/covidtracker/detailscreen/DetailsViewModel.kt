package com.app.covidtracker.detailscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {
    val activeCasesLiveData = MutableLiveData<Int>()
    val deathsCasesLiveData = MutableLiveData<Int>()
    val confirmedCasesLiveData = MutableLiveData<Int>()
    val totalCasesLiveData = MutableLiveData<Int>()
}