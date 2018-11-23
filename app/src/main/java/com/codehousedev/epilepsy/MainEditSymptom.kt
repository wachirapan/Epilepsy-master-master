package com.codehousedev.epilepsy

import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import com.codehousedev.sqlite.QuerySQLite
import com.codehousedev.sqlite.UpdateSQLite

class MainEditSymptom : AppCompatActivity() {
    lateinit var symptom_id : String
    lateinit var cursor : Cursor
    lateinit var numberhour : String
    lateinit var numberminute : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_edit_symptom)
        symptom_id = getIntent().getStringExtra("id")
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


        val querysymptom = QuerySQLite(this)
        cursor = querysymptom.querysymptomdetail(symptom_id)
        while(cursor.moveToNext()){
            mcause.setText(cursor.getString(1))
            mtype.setText(cursor.getString(2))
            mstimulant.setText(cursor.getString(3))
            mlocation.setText(cursor.getString(4))
            mlengthdata.setText(cursor.getString(5))
            mdurationone.setValue(cursor.getString(6).toInt())
            mdurationtwo.setValue(cursor.getString(7).toInt())
        }
        var updatesymtom : Button = findViewById(R.id.updatesymtom)
        updatesymtom.setOnClickListener({
            var updatesymptom = UpdateSQLite(this)
            updatesymptom.updatesymptom(symptom_id,mcause.text.toString(),mtype.text.toString(),mstimulant.text.toString(),mlocation.text.toString(),
                    mlengthdata.text.toString(),numberhour,numberminute)
            var backsymptomdetail = Intent(this,MainSymptoms::class.java)
            startActivity(backsymptomdetail)
        })
    }
}
