package com.example.myapplication.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.covidtracker.R
import com.example.myapplication.covidapi.response.Country
import kotlinx.android.synthetic.main.country_item_layout.view.*


class CountryListAdapter (val clickListener: (Country) -> Unit): RecyclerView.Adapter<CountryListAdapter.CountViewHolder>() {

    private var countries: List<Country> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountViewHolder {
        return CountViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country_item_layout, parent, false),
            clickListener
        )
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    class CountViewHolder(itemView: View,
                          val callback: (Country) -> Unit) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val countryView = itemView.countryTextView

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(country: Country) {
            countryView.text = country.name
            itemView.tag = country
        }

        override fun onClick(v: View) {
            callback(v.tag as Country)
        }

    }

    fun submitList(countries: List<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }

}