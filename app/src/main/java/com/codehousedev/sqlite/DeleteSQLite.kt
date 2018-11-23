package com.codehousedev.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DeleteSQLite(context: Context?) : ConnectSQLite(context) {
    lateinit var db : SQLiteDatabase
    fun delsymptoms(symptomid : String)
    {
        db = this.writableDatabase
        db.delete("symptoms","symptoms_id = ?", arrayOf(symptomid))
    }
    fun delsettiming(settime_id : String)
    {
        db = this.writableDatabase
        db.delete("settimedrug","settimedrug_id = ?", arrayOf(settime_id))
    }
}