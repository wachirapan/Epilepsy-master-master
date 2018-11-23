package com.codehousedev.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class InsertSQLite(context: Context?) : ConnectSQLite(context) {
    lateinit var db: SQLiteDatabase

    fun insertprofile(profile_name: String, profile_age: String, profile_tel: String,
                      profile_treatment: String, profile_cause: String, profile_type: String, profile_stimulant: String,
                      profile_sideeffect: String, profile_allergyhistory: String) {
        db = this.writableDatabase
        var values = ContentValues()
        values.put("profile_name", profile_name)
        values.put("profile_age", profile_age)
        values.put("profile_tel", profile_tel)
        values.put("profile_treatment", profile_treatment)
        values.put("profile_cause", profile_cause)
        values.put("profile_type", profile_type)
        values.put("profile_stimulant", profile_stimulant)
        values.put("profile_sideeffect", profile_sideeffect)
        values.put("profile_allergyhistory", profile_allergyhistory)
        db.insert("profile", null, values)
    }

    fun insertsymptom(symptoms_cause: String, symptoms_type: String, symptoms_stimulant: String, symptoms_location: String,
                      symptoms_length: String, symptom_durationhour: String, symptom_durationminute: String) {
        db = this.writableDatabase
        var values = ContentValues()

        values.put("symptoms_cause", symptoms_cause)
        values.put("symptoms_type", symptoms_type)
        values.put("symptoms_stimulant", symptoms_stimulant)
        values.put("symptoms_location", symptoms_location)
        values.put("symptoms_length", symptoms_length)
        values.put("symptom_durationhour", symptom_durationhour)
        values.put("symptom_durationminute", symptom_durationminute)
        values.put("symptom_status", "n")
        db.insert("symptoms", null, values)
    }

    fun insertsettimedrug(settime_durationhour: String, settime_durationminute: String,
                          settime_category: String, settime_namedrug: String, timeouts: String, fulltime : String):Long {
        db = this.writableDatabase
        var values = ContentValues()
        values.put("settime_durationhour", settime_durationhour)
        values.put("settime_durationminute", settime_durationminute)
        values.put("settime_category", settime_category)
        values.put("settime_namedrug", settime_namedrug)
        values.put("settime_amount", timeouts)
        values.put("settime_fulltime",fulltime)
        var row : Long = db.insert("settimedrug",null, values)
        db.close()
        return row
    }
}


