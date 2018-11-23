package com.codehousedev.epilepsy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import com.codehousedev.sqlite.InsertSQLite

class MainInsertSymptom : AppCompatActivity() {
    lateinit var numberhour :  String
    lateinit var numberminute : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_insert_symptom)
        initdata()
    }
    fun initdata()
    {
        var mcause : EditText = findViewById(R.id.cause)
        var mtype : EditText = findViewById(R.id.type)
        var mstimulant : EditText = findViewById(R.id.stimulant)
        var mlocation : EditText = findViewById(R.id.location)
        var mlengthdata : EditText = findViewById(R.id.lengthdata)
        var mdurationone : NumberPicker = findViewById(R.id.durationone)
        mdurationone.setMinValue(0)
        mdurationone.setMaxValue(24)
        mdurationone.setWrapSelectorWheel(true)
        mdurationone.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            //Display the newly selected number from picker
            numberhour = newVal.toString()
        })
        var mdurationtwo : NumberPicker = findViewById(R.id.durationtwo)
        mdurationtwo.setMinValue(1)
        mdurationtwo.setMaxValue(60)
        mdurationtwo.setWrapSelectorWheel(true)
        mdurationtwo.setOnValueChangedListener(NumberPicker.OnValueChangeListener{ picker, oldVal, newVal ->
            numberminute = newVal.toString()
        })

        var minsertsymtom : Button = findViewById(R.id.insertsymtom)

        minsertsymtom.setOnClickListener({
            var insertdata = InsertSQLite(this)

            insertdata.insertsymptom(mcause.text.toString(),mtype.text.toString(),mstimulant.text.toString(),mlocation.text.toString(),
                    mlengthdata.text.toString(),numberhour,numberminute)
            var backsymptom = Intent(this,MainSymptoms::class.java)
            startActivity(backsymptom)
        })
    }
}
