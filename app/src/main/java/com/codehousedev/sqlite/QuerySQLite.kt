package com.codehousedev.sqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class QuerySQLite(context: Context?) : ConnectSQLite(context!!) {
    lateinit var db : SQLiteDatabase
    lateinit var cursor : Cursor
    fun queryprofile() : Cursor{
        db = this.writableDatabase
        cursor = db.rawQuery("select * from profile",null)
        return cursor
    }
    fun queryeditprofile(productid : String) : Cursor{
        db = this.writableDatabase
        cursor = db.rawQuery("select * from profile where profile_id = "+productid,null)
        return cursor
    }
    fun querysymptoms() : Cursor
    {
        db = this.writableDatabase
        cursor = db.rawQuery("select * from symptoms where symptom_status = 'n' ",null)
        return cursor
    }
    fun querysymptomdetail(symptomid : String) : Cursor
    {
        db = this.writableDatabase
        cursor = db.rawQuery("select * from symptoms where symptoms_id = "+ symptomid,null)
        return cursor
    }
    fun querysettimedrug() :Cursor
    {
        db = this.writableDatabase
        cursor = db.rawQuery("select * from settimedrug",null)
        return cursor
    }
    fun querysuccesssymptom() : Cursor
    {
        db = this.writableDatabase
        cursor = db.rawQuery("select * from symptoms where symptom_status = 'y' ",null)
        return cursor
    }
    fun querydatatime(h : String, m : String) : Cursor
    {
        db = this.writableDatabase
        cursor = db.rawQuery("select * from settimedrug where settime_durationhour = " + h + " and settime_durationminute = "+ m +" ",null)
        return cursor
    }
    fun queryreportdate(date : String, date2 : String) : Cursor
    {
        db = this.writableDatabase
        cursor = db.rawQuery("select * from symptoms where symptoms_time >= ' "+date+"' and symptoms_time <= '"+date2+"'",null)
        return cursor
    }
}