package com.codehousedev.epilepsy

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.app.TimePickerDialog
import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.text.format.DateFormat
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.codehousedev.data.SymptomsData
import com.codehousedev.sqlite.QuerySQLite
import com.codehousedev.sqlite.UpdateSQLite
import com.codehousedev.submain.SubClassReporter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*
import android.widget.TimePicker
import android.widget.DatePicker



class MainAfterReport : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    var mYear: Int = 0
    var mMonth:Int = 0
    var mDay:Int = 0
    var mHour:Int = 0
    var mMinute:Int = 0
    lateinit var mlistreporter : ListView
    lateinit var mlist : ArrayList<SymptomsData>
    lateinit var mcursor : Cursor
    lateinit var btnsearch : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_after_report)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        initdata()
        querylist()

    }
    fun initdata()
    {
        mlistreporter = findViewById(R.id.listreporter)
        btnsearch = findViewById(R.id.btnsearch)
        btnsearch.setOnClickListener({
            var intent = Intent(this, SearchDate::class.java)
            startActivity(intent)
        })
    }


    fun querylist()
    {
        mlist = ArrayList()
        var querysymptom = QuerySQLite(this)
        mcursor = querysymptom.querysuccesssymptom()
        while (mcursor.moveToNext()){
            var symptomid = mcursor.getInt(0)
            var headdetail = mcursor.getString(1)
            var datedetail = mcursor.getString(9)
            mlist.add(SymptomsData(symptomid!!, headdetail,datedetail))
        }
        var Subsymptom = SubClassReporter(this,mlist)
        mlistreporter.setAdapter(Subsymptom)

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_main -> {
                var intentmain = Intent(this,MainActivity::class.java)
                startActivity(intentmain)
            }

            R.id.nav_slideshow -> {
                var intentsymptoms = Intent(this,MainSymptoms::class.java)
                startActivity(intentsymptoms)
            }
            R.id.nav_manage -> {
                var intentsettime = Intent(this,MainSettime::class.java)
                startActivity(intentsettime)
            }
            R.id.nav_share -> {
                var intentreporter = Intent(this,MainReporter::class.java)
                startActivity(intentreporter)
            }
            R.id.nav_send ->{
                var intentafterReport = Intent(this,MainAfterReport::class.java)
                startActivity(intentafterReport)
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
