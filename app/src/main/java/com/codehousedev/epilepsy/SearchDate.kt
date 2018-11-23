package com.codehousedev.epilepsy

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import java.util.*
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.app.DatePickerDialog
import android.view.View


class SearchDate : Activity(), View.OnClickListener {
    var mYear: Int = 0
    var mMonth:Int = 0
    var mDay:Int = 0
    var mYear1: Int = 0
    var mMonth1:Int = 0
    var mDay1:Int = 0

    lateinit var date1 : EditText
    lateinit var date2 : EditText
    lateinit var btnsearch : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_date)

        init()
    }
    fun init()
    {
        date1 = findViewById(R.id.txtdate1)
        date1.setOnClickListener(this)

        date2 = findViewById(R.id.txtdate2)
        date2.setOnClickListener(this)
        btnsearch = findViewById(R.id.btnsearch)
        btnsearch.setOnClickListener({
            var intent = Intent(this,ReportSearch::class.java)
            intent.putExtra("before", date1.text.toString())
            intent.putExtra("after",date2.text.toString())
            startActivity(intent)
        })
    }

    override fun onClick(v: View) {

        if (v === date1) {

            // Get Current Date
            val c = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth -> date1.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) }, mYear, mMonth, mDay)
            datePickerDialog.show()
        }
        if (v === date2) {

            val c = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth -> date2.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) }, mYear, mMonth, mDay)
            datePickerDialog.show()
        }
    }

}


