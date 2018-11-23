package com.codehousedev.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class ConnectSQLite(context: Context?) : SQLiteOpenHelper(context, "elilepsy.db", null, 1) {
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE profile " +
                "(profile_id INTEGER PRIMARY KEY," +
                " profile_name TEXT(100)," +
                " profile_age TEXT(10)," +
                " profile_tel TEXT(15)," +
                " profile_treatment TEXT(100)," +
                " profile_cause TEXT(100)," +
                " profile_type TEXT(100)," +
                " profile_stimulant TEXT(100)," +
                " profile_sideeffect TEXT(100)," +
                " profile_allergyhistory TEXT(100))")
        db!!.execSQL("CREATE TABLE settimedrug " +
                "(settimedrug_id INTEGER PRIMARY KEY," +
                " settime_durationhour TEXT," +
                " settime_durationminute TEXT," +
                " settime_category TEXT," +
                " settime_namedrug TEXT," +
                " settime_amount TEXT," +
                " settime_fulltime TEXT)")
        db!!.execSQL("CREATE TABLE symptoms " +
                "(symptoms_id INTEGER PRIMARY KEY," +
                " symptoms_cause TEXT," +
                " symptoms_type TEXT," +
                " symptoms_stimulant TEXT," +
                " symptoms_location TEXT," +
                " symptoms_length TEXT," +
                " symptom_durationhour TEXT," +
                " symptom_durationminute TEXT," +
                " symptom_status TEXT," +
                " symptoms_time DATETIME DEFAULT CURRENT_TIMESTAMP)")



    }
}