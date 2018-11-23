package com.codehousedev.epilepsy

import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.Button
import android.widget.ListView
import com.codehousedev.data.SymptomsData
import com.codehousedev.sqlite.QuerySQLite
import com.codehousedev.submain.SubClassSymptom
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.sub_main_symptom.*

class MainSymptoms : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    lateinit var mlist : ArrayList<SymptomsData>
    lateinit var mcursor : Cursor
    var symptomid : Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_symptoms)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        initsymptom()
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
    fun initsymptom()
    {
        var listSymptoms : ListView = findViewById(R.id.listsymptoms)

        mlist = ArrayList()
        var querysymptom = QuerySQLite(this)
        mcursor = querysymptom.querysymptoms()
        while (mcursor.moveToNext()){
            symptomid = mcursor.getInt(0)
            var headdetail = mcursor.getString(1)
            var datedetail = mcursor.getString(9)
            mlist.add(SymptomsData(symptomid!!, headdetail,datedetail))
        }
        var Subsymptom = SubClassSymptom(this,mlist)
        listSymptoms.setAdapter(Subsymptom)
        var minsertsymptoms : Button = findViewById(R.id.insertsymptoms)
        minsertsymptoms.setOnClickListener({
            var insertsymtom = Intent(this,MainInsertSymptom::class.java)
            startActivity(insertsymtom)
        })
    }
}
