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
import com.codehousedev.data.SettimingDrug
import com.codehousedev.sqlite.QuerySQLite
import com.codehousedev.submain.SubClassSettime
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainSettime : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var mlist : ArrayList<SettimingDrug>
    lateinit var ms : Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_settime)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        initdata()
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
        mlist = ArrayList()
        var mlistviewsettiming : ListView = findViewById(R.id.listviewsettiming)
        var getquery = QuerySQLite(this)
        ms = getquery.querysettimedrug()
        while (ms.moveToNext()){
            var setid : Int = ms.getInt(0)
            var settime_durationhour : String = ms.getString(1)
            var settime_durationminute : String = ms.getString(2)
            var settime_category : String = ms.getString(3)
            var settime_namedrug : String = ms.getString(4)
            var settime_amount : String = ms.getString(5)
            var settime_fulltime : String = ms.getString(6)
            mlist.add(SettimingDrug(setid, settime_durationhour, settime_durationminute, settime_category
                    ,settime_namedrug, settime_amount,settime_fulltime
            ))
        }
        var getsubset = SubClassSettime(this, mlist)
        mlistviewsettiming.setAdapter(getsubset)

        var btninset : Button = findViewById(R.id.btninsertgrug)
        btninset.setOnClickListener({
            var inerttime = Intent(this,SetTime::class.java)
            startActivity(inerttime)
        })
    }
}
