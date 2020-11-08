package com.app.covidtracker.detailscreen

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class DatePickerFragment : DialogFragment() , DatePickerDialog.OnDateSetListener  {
    @Override
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c =     Calendar.getInstance();
        val year =  c.get(Calendar.YEAR);
        val month = c.get(Calendar.MONTH);
        val day =   c.get(Calendar.DAY_OF_MONTH);

        return  DatePickerDialog(requireActivity(),
            this,
            year, month, day);
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val c: Calendar = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val currentDateString = "${year}-${month}-${dayOfMonth}"

        targetFragment!!.onActivityResult(
            targetRequestCode,
            Activity.RESULT_OK,
            Intent().putExtra("selectedDate", currentDateString)
        )
    }
}