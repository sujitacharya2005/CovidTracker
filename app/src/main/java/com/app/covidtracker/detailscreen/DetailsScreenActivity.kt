package com.app.covidtracker.detailscreen

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.app.covidtracker.R
import com.app.covidtracker.constant.Constants

import com.google.android.material.tabs.TabLayout

class DetailsScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_screen)
        val slug = intent.getStringExtra(Constants.SLUG)
        val sectionsPagerAdapter =
            ViewPagerFragment(
                this,
                supportFragmentManager,
                slug?: "india"
            )
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

    }

}