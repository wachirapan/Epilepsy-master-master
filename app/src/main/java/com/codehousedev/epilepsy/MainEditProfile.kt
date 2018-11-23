package com.codehousedev.epilepsy

import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.view.View
import android.widget.*
import com.codehousedev.sqlite.QuerySQLite
import com.codehousedev.sqlite.UpdateSQLite
import android.widget.ArrayAdapter



class MainEditProfile : AppCompatActivity() {
    lateinit var productid : String
    lateinit var cursor : Cursor

    lateinit var treatment : ArrayList<String>
    lateinit var mtype : ArrayList<String>
    lateinit var stimulant : ArrayList<String>
    lateinit var sideeffect :ArrayList<String>
    lateinit var mtxttreatment : Spinner
    lateinit var mtxtname : EditText
    lateinit var mtxtage : EditText
    lateinit var mtxttell : EditText
    lateinit var mtxtcause : EditText
    lateinit var mtxtallergyhistory : EditText
    lateinit var mtxttype : Spinner
    lateinit var mtxtstimulant : Spinner
    lateinit var mtxtsideeffect : Spinner
    lateinit var gettreatment : String
    lateinit var getmtype : String
    lateinit var getstimulant : String
    lateinit var getsideeffect : String

    lateinit var m_Texttreatment : String
    lateinit var m_Textmtype : String
    lateinit var m_Textstimulant : String
    lateinit var m_Textsideeffect : String

    lateinit var datatreatment : String
    lateinit var datatype : String
    lateinit var datastimulant : String
    lateinit var datasideeffect : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_edit_profile)
        productid = getIntent().getStringExtra("productid")
        init()


    }
    fun init()
    {
        mtxtname  = findViewById(R.id.txtname)
        mtxtage = findViewById(R.id.txtage)
        mtxttell = findViewById(R.id.txttell)
        mtxttreatment  = findViewById(R.id.txttreatment)
        mtxtcause = findViewById(R.id.txtcause)
        mtxttype = findViewById(R.id.txttype)
        mtxtstimulant = findViewById(R.id.txtstimulant)
        mtxtsideeffect = findViewById(R.id.txtsideeffect)
        mtxtallergyhistory = findViewById(R.id.txtallergyhistory)
        var detaileditprofile = QuerySQLite(this)
        val list = ArrayList<String>()
        cursor = detaileditprofile.queryeditprofile(productid)
        while (cursor.moveToNext()){
            mtxtname.setText(cursor.getString(1))
            mtxtage.setText(cursor.getString(2))
            mtxttell.setText(cursor.getString(3))
            datatreatment = cursor.getString(4)
            mtxtcause.setText(cursor.getString(5))
            datatype = cursor.getString(6)
            datastimulant = cursor.getString(7)
            datasideeffect = cursor.getString(8)
            mtxtallergyhistory.setText(cursor.getString(9))
        }

        treatment = ArrayList()
        treatment.add(datatreatment)
        treatment.add("สิทธิสวัสการของข้าราชการ")
        treatment.add("สิทธิประกันสังคม")
        treatment.add("สิทธิประกันสุขภาพ 30 บาท")
        val settreatment = ArrayAdapter(this,android.R.layout.simple_spinner_item,treatment)
        settreatment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mtxttreatment.setAdapter(settreatment)
        mtxttreatment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                gettreatment = treatment[p2]
                if(gettreatment == "อื่นๆ"){
                    dialogtreatmentdata()
                }else{
                    gettreatment = treatment[p2]
                }
            }
        }

        mtype = ArrayList()
        mtype.add(datatype)
        mtype.add("การชักที่ทำอะไรโดยไม่รู้ตัว ทำซ้ำๆ")
        mtype.add("การชักที่มีลักษณะล้มลง เกร็ง กระตุกทั้งตัวเป็นพักๆ")
        mtype.add("การชักที่ตาเหม่อลอย ระยะสั้น")
        mtype.add("การชักกระตุก แขนหรือขาชาเฉพาะที่ รู้สติดี")
        mtype.add("การชักที่มีลักษณะล้มลง เกร็ง กระตุกทั้งตัวเป็นพักๆ")
        mtype.add("การชักกระตุกทั้งตัวครั้งเดียว หมดสติ")
        mtype.add("อื่นๆ")
        val setmtype = ArrayAdapter(this,android.R.layout.simple_spinner_item,mtype)
        setmtype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mtxttype.setAdapter(setmtype)
        mtxttype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getmtype = mtype[p2]
                if(getmtype == "อื่นๆ"){
                    dialogtypedata()
                }else{
                    getmtype = mtype[p2]
                }
            }
        }
        stimulant = ArrayList()
        stimulant.add(datastimulant)
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
        sideeffect = ArrayList()
        sideeffect.add(datasideeffect)
        sideeffect.add("คลื่นไส้ อาเจียน")
        sideeffect.add("ง่วง")
        sideeffect.add("เดินเซ")
        sideeffect.add("อาการมากขึ้น")
        sideeffect.add("น้ำหนักขึ้น")
        sideeffect.add("ผมร่วง")
        sideeffect.add("เหงือกบวม")
        sideeffect.add("เบื่ออาการ")
        sideeffect.add("อื่นๆ")
        val setsideeffect = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,sideeffect)
        setsideeffect.setDropDownViewResource(android.R.layout.simple_spinner_item)
        mtxtsideeffect.setAdapter(setsideeffect)
        mtxtsideeffect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getsideeffect = sideeffect[p2]
                if(getsideeffect == "อื่นๆ"){
                    dialogsideeffectdata()
                }else{
                    getsideeffect = sideeffect[p2]
                }
            }
        }

        var submitprofile : Button = findViewById(R.id.submitprofile)
        var updateprofile = UpdateSQLite(this)
        submitprofile.setOnClickListener({
            updateprofile.updateprofile(productid,mtxtname.text.toString(),mtxtage.text.toString(),mtxttell.text.toString(),
                    gettreatment,mtxtcause.text.toString(),getmtype,
                    getstimulant,getsideeffect,mtxtallergyhistory.text.toString())
            var backprofile = Intent(this,MainHistory::class.java)
            startActivity(backprofile)
        })
    }
    fun dialogtreatmentdata()
    {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("สิทธ์การรักษา")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            m_Texttreatment = input.text.toString()
            gettreatment = m_Texttreatment })

        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
    fun dialogtypedata()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Title")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> m_Textmtype = input.text.toString()
            getmtype = m_Textmtype})
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
    fun dialogsideeffectdata()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Title")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> m_Textsideeffect = input.text.toString()
            getsideeffect = m_Textsideeffect})
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
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
}
