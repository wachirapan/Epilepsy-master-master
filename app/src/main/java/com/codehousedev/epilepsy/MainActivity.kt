package com.codehousedev.epilepsy

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import com.codehousedev.epilepsy.R.id.*
import com.codehousedev.sqlite.ConnectSQLite
import com.codehousedev.sqlite.QuerySQLite
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var mtextdailog : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        var createdb = ConnectSQLite(this)
        createdb.writableDatabase

        initdata()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
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
    fun initdata()
    {
        var suggestion : Button = findViewById(R.id.btnsuggestion)
        var history : Button = findViewById(R.id.btnhistory)
        suggestion.setOnClickListener({
            var gosuggestion = Intent(this,MainSuggestion::class.java)
            startActivity(gosuggestion)
        })
        history.setOnClickListener({
            var gohistory = Intent(this,MainHistory::class.java)
            startActivity(gohistory)
        })
    }

}
