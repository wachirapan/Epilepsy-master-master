package com.codehousedev.data

class SettimingDrug {
    var settime_id : Int? = 0
    var settime_durationhour : String? = null /*ช่วงเวลาชั่วโมง*/
    var settime_durationminute : String? = null /* ช่วงเวลานาที*/
    var settime_category : String? = null /*ก่อน หลัง อาหาร*/
    var settime_namedrug : String? = null /*ชื่อยา*/
    var settime_amount : String? = null /*จำนวน*/
    var settime_fulltime : String? = null
    constructor(settime_id : Int, settime_durationhour : String, settime_durationminute : String,
                settime_category : String, settime_namedrug : String,
                settime_amount : String, settime_fulltime : String){
        this.settime_id = settime_id
        this.settime_durationhour = settime_durationhour
        this.settime_durationminute = settime_durationminute
        this.settime_category = settime_category
        this.settime_namedrug = settime_namedrug
        this.settime_amount = settime_amount
        this.settime_fulltime = settime_fulltime
    }
    constructor(settime_durationhour : String, settime_durationminute : String, settime_category : String, settime_namedrug : String,
                settime_amount : String){
        this.settime_durationhour = settime_durationhour
        this.settime_durationminute = settime_durationminute
        this.settime_category = settime_category
        this.settime_namedrug = settime_namedrug
        this.settime_amount = settime_amount

    }

}