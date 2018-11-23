package com.codehousedev.epilepsy

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.codehousedev.sqlite.QuerySQLite

class SymptomDetail : AppCompatActivity() {
    lateinit var symptomid : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptom_detail)

        symptomid = getIntent().getExtras().getString("Symptom_id");

        initdataxml()
    }
    fun initdataxml()
    {
        var mcause : TextView = findViewById(R.id.cause)
        var mtype : TextView = findViewById(R.id.type)
        var mstimulant : TextView = findViewById(R.id.stimulant)
        var mlocation : TextView = findViewById(R.id.location)
        var mlengthdata : TextView = findViewById(R.id.lengthdata)
        var mduration : TextView = findViewById(R.id.duration)
        var mtxtdetaildate : TextView = findViewById(R.id.txtdetaildate)
        var query = QuerySQLite(this)
        var cs : Cursor = query.querysymptomdetail(symptomid)
        while (cs.moveToNext()){
            mcause.setText(cs.getString(1))
            mtype.setText(cs.getString(2))
            mstimulant.setText(cs.getString(3))
            mlocation.setText(cs.getString(4))
            mlengthdata.setText(cs.getString(5))
            mduration.setText(cs.getString(6)+" ชั่วไมง "+cs.getString(7)+" นาที")
            mtxtdetaildate.setText(cs.getString(9))
        }
    }
}
