package com.app.covidtracker.detailscreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.covidtracker.R
import com.app.covidtracker.constant.Constants
import com.app.covidtracker.covidapi.response.CovidSatus
import com.app.covidtracker.covidapi.response.CovidSatusDateWise
import com.example.myapplication.covidapi.CovidApiClient
import kotlinx.android.synthetic.main.fragment_counry_status.*
import kotlinx.android.synthetic.main.fragment_date_wise.*
import kotlinx.android.synthetic.main.fragment_date_wise.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DateWiseFragment : Fragment() {
    var selectedDate: String? = null
    val REQUEST_CODE = 100
    lateinit var button: Button
    private var slug: String? = null
    lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            slug = it.getString(Constants.SLUG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_date_wise, container, false)
        getStatusDetailsApi()
        button = view.button
        view.button.setOnClickListener {
            val datePicker = DatePickerFragment();
            datePicker.setTargetFragment(this, REQUEST_CODE)
            datePicker.show(activity!!.supportFragmentManager, "date picker");
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[DetailsViewModel::class.java]
        viewModel.totalCasesLiveData.observe(viewLifecycleOwner, Observer<Int> { t ->
            cases_count.text = t.toString()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // check for the results
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // get date from string
            selectedDate = data!!.getStringExtra("selectedDate");

            button.text = selectedDate

        }
    }

    private fun getStatusDetailsApi() {
        slug?.let {
            CovidApiClient.create().getStatusDateWise(it)
                .enqueue(object : Callback<List<CovidSatusDateWise>> {
                    override fun onFailure(call: Call<List<CovidSatusDateWise>>, t: Throwable) {
                        Log.d("retrolog", "error: " + t.message)
                    }

                    override fun onResponse(
                        call: Call<List<CovidSatusDateWise>>,
                        response: Response<List<CovidSatusDateWise>>
                    ) {
                        if (response.body()!!.size > 0) {

                            Log.d(
                                "retrolog", "onResponse: "
                                        + (response.body() as List<CovidSatusDateWise>)[(response.body())!!.size - 1]
                            )
                            val status =
                                (response.body() as List<CovidSatusDateWise>)[(response.body())!!.size - 1]
                            viewModel.totalCasesLiveData.value = status.Cases
                        }
                    }

                })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            DateWiseFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.SLUG, param1)
                }
            }
    }
}