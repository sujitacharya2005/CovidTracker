package com.example.myapplication.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.covidapi.response.Country


class CountryListViewModel : ViewModel() {
    private val countryList: MutableLiveData<List<Country>> = CountriesRepository.getCountries()

    fun getAllCountries(): LiveData<List<Country>> {
        return countryList
    }
}