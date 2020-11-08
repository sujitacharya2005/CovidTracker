package com.app.covidtracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.appbar.CollapsingToolbarLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.covidapi.response.Country
import com.example.myapplication.onboarding.CountryListAdapter
import com.example.myapplication.onboarding.CountryListViewModel
import kotlinx.android.synthetic.main.activity_covid_tracker.*
import kotlinx.android.synthetic.main.content_main.*

class CovidTrackerActivity : AppCompatActivity() {

    private lateinit var countryListAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_tracker)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        initRecyclerView()
        initViewModel()
    }
    private fun initRecyclerView(){
        recycler_view.apply {
            addItemDecoration(
                DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
            )

            countryListAdapter = CountryListAdapter { item -> itemClicked(item) }
            adapter = countryListAdapter

        }
    }
    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(CountryListViewModel::class.java)
        viewModel.getAllCountries().observe(this, Observer<List<Country>>{ users ->
            Log.d("TAG1", "initViewModel: "+users)
            countryListAdapter.submitList(users)
            progressBar.setVisibility(View.GONE);
        })
    }
    private fun itemClicked(country: Country) {
        Toast.makeText(this, "Clicked: ${country.name}", Toast.LENGTH_LONG).show()
    }

}