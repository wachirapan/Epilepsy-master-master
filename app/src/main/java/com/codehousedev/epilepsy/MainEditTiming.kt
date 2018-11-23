package com.codehousedev.epilepsy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import com.codehousedev.sqlite.UpdateSQLite

class MainEditTiming : Activity() {
    var id : String = ""
    var namedrug : String = ""
    var hour : String = ""
    var minute : String = ""
    var mcategory : String = ""
    var total : String = ""
    lateinit var timimghour : Spinner
    lateinit var timimgmunite : Spinner
    lateinit var category : Spinner
    lateinit var textdrugname : EditText
    lateinit var textamount : Spinner

    lateinit var texthour : ArrayList<String>
    lateinit var textminute : ArrayList<String>
    lateinit var textcate : ArrayList<String>
    lateinit var mamount : ArrayList<String>
    lateinit var getdatahour : String
    lateinit var getdataaminute : String
    lateinit var getdatacate : String
    lateinit var getdataamount : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_edit_timing)

        id = getIntent().getStringExtra("id")
        namedrug = getIntent().getStringExtra("namedrug")
        hour = getIntent().getStringExtra("hour")
        minute = getIntent().getStringExtra("minute")
        mcategory = getIntent().getStringExtra("category")
        total = getIntent().getStringExtra("total")

        getinit()
    }
    fun getinit()
    {
        timimghour = findViewById(R.id.timimghour)
        timimgmunite = findViewById(R.id.timimgmunite)
        category = findViewById(R.id.category)
        textdrugname = findViewById(R.id.textdrugname)
        textamount = findViewById(R.id.textamount)
        setdata()
    }
    protected fun setdata()
    {
        textdrugname.setText(namedrug)
        texthour = ArrayList()
        for (i in 1..24){
            texthour.add(i.toString())
        }

        val settimimghour = ArrayAdapter(this,android.R.layout.simple_spinner_item, texthour)
        settimimghour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timimghour!!.setAdapter(settimimghour)
    if(hour != null){
        val spinnerPosition = settimimghour.getPosition(hour)
        timimghour.setSelection(spinnerPosition)
    }
        timimghour.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getdatahour = texthour[p2]
            }
        }
        textminute = ArrayList()
        for (j in 1..60){
            textminute.add(j.toString())
        }

        val settiminute = ArrayAdapter(this,android.R.layout.simple_spinner_item, textminute)
        settiminute.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timimgmunite!!.setAdapter(settiminute)
        if(minute != null){
            val spinnerPosition = settiminute.getPosition(minute)
            timimgmunite.setSelection(spinnerPosition)
        }
        timimgmunite.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getdataaminute = textminute[p2]
            }
        }
        textcate = ArrayList()
        textcate.add("กรุณาเลือกช่วงเวลา")
        textcate.add("ก่อนอาหาร")
        textcate.add("หลังอาหาร")
        val setcate = ArrayAdapter(this,android.R.layout.simple_spinner_item, textcate)
        setcate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        category!!.setAdapter(setcate)
    if(mcategory != null){
        val spinnerPosition = setcate.getPosition(mcategory)
        category.setSelection(spinnerPosition)
        }
        category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getdatacate = textcate[p2]
            }
        }
        mamount = ArrayList()
        mamount.add("0")
        mamount.add("1")
        mamount.add("2")
        mamount.add("3")
        mamount.add("4")
        mamount.add("5")
        mamount.add("6")
        mamount.add("7")
        mamount.add("8")
        mamount.add("9")
        mamount.add("10")
        val setamount = ArrayAdapter(this,android.R.layout.simple_spinner_item, mamount)
        setamount.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        textamount.setAdapter(setamount)
        if(total != null){
            val spinnerPosition = setamount.getPosition(total)
            textamount.setSelection(spinnerPosition)
        }
        textamount.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getdataamount = mamount[p2]
            }
        }
        var btnedit :Button = findViewById(R.id.btninsert)
        btnedit.setOnClickListener({
            var updatedata = UpdateSQLite(this)
            updatedata.updatetiming(id,getdatahour,getdataaminute,getdatacate,textdrugname.text.toString(),getdataamount)
            var back = Intent(this,MainSettime::class.java)
            startActivity(back)
        })
    }
}
