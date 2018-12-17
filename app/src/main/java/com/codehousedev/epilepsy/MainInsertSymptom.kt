package com.codehousedev.epilepsy

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.view.View
import android.widget.*
import com.codehousedev.sqlite.InsertSQLite

class MainInsertSymptom : AppCompatActivity() {
    lateinit var numberhour :  String
    lateinit var numberminute : String
    lateinit var mtxtstimulant : Spinner
    lateinit var mtype : Spinner
    lateinit var stimulant : ArrayList<String>
    lateinit var datatype : ArrayList<String>
    lateinit var getstimulant : String
    lateinit var getdatatype : String
    lateinit var m_Textstimulant : String
    lateinit var m_Texttype : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_insert_symptom)
        initdata()
    }
    fun initdata()
    {
        var mcause : EditText = findViewById(R.id.cause)
        mtype  = findViewById(R.id.type)
        mtxtstimulant = findViewById(R.id.txtstimulant)
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
        datatype = ArrayList()
        datatype.add("การชักที่ทำโดยไม่รู็สึกตัว")
        datatype.add("การชักที่มีลักษณะล้มลง เกร็ง กระตุกทั้งตัวเป็นพักๆ")
        datatype.add("การชักที่ตาเหม่อลอย ระยะสั้น")
        datatype.add("การชักกระตุก แขนหรือขาชาเฉพาะที่ รู้สติดี")
        datatype.add("การชักที่มีลักษณะทั้งตัว หมดสติ")
        datatype.add("อื่นๆ")
        val settype = ArrayAdapter(this,android.R.layout.simple_spinner_item,datatype)
        settype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mtype.setAdapter(settype)
        mtype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getdatatype = datatype[p2]
                if(getdatatype == "อื่นๆ"){
                    dialogdatatype()
                }else{
                    getdatatype = datatype[p2]
                }
            }
        }

        stimulant = ArrayList()
        stimulant.add("ไข้สูง")
        stimulant.add("เครือ่งดื่มแอลกอฮอล์")
        stimulant.add("การอดนอน")
        stimulant.add("ความเครียด")
        stimulant.add("ประจำเดือน")
        stimulant.add("อื่นๆ")
        val setstimulant = ArrayAdapter(this,android.R.layout.simple_spinner_item,stimulant)
        setstimulant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mtxtstimulant.setAdapter(setstimulant)
        mtxtstimulant.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getstimulant = stimulant[p2]
                if(getstimulant == "อื่นๆ"){
                    dialogstimulantdata()
                }else{
                    getstimulant = stimulant[p2]
                }
            }
        }
        var minsertsymtom : Button = findViewById(R.id.insertsymtom)

        minsertsymtom.setOnClickListener({
            var insertdata = InsertSQLite(this)

            insertdata.insertsymptom(mcause.text.toString(),getdatatype,getstimulant,mlocation.text.toString(),
                    mlengthdata.text.toString(),numberhour,numberminute)
            var backsymptom = Intent(this,MainSymptoms::class.java)
            startActivity(backsymptom)
        })
    }
    fun dialogstimulantdata()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Title")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> m_Textstimulant = input.text.toString()
            getstimulant = m_Textstimulant})
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
    fun dialogdatatype(){
        val builders = AlertDialog.Builder(this)
        builders.setTitle("Title")

        val inputs = EditText(this)
        inputs.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS
        builders.setView(inputs)

        builders.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> m_Texttype = inputs.text.toString()
            getdatatype = m_Texttype})
        builders.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builders.show()
    }
}
