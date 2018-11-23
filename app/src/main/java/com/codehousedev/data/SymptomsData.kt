package com.codehousedev.data

class SymptomsData {
    var symptoms_id : Int = 0
    var symptoms_cause : String? = null
    var symptoms_type : String? = null
    var symptoms_stimulant : String? = null
    var symptoms_location : String? = null
    var symptoms_length : String? = null
    var symptom_durationhour : String? = null
    var symptom_durationminute : String? = null
    var symptoms_time : String? = null
    constructor(symptoms_id : Int, symptoms_cause : String, symptoms_type : String, symptoms_stimulant : String,
                symptoms_location : String, symptoms_length : String, symptom_durationhour : String,symptom_durationminute : String
                , symptoms_time : String){
        this.symptoms_id = symptoms_id
        this.symptoms_cause = symptoms_cause
        this.symptoms_type = symptoms_type
        this.symptoms_stimulant = symptoms_stimulant
        this.symptoms_location = symptoms_location
        this.symptoms_length = symptoms_length
        this.symptom_durationhour = symptom_durationhour
        this.symptom_durationminute = symptom_durationminute
        this.symptoms_time = symptoms_time
    }

    constructor(symptoms_id: Int, symptoms_cause : String,symptoms_time : String){
        this.symptoms_id = symptoms_id
        this.symptoms_cause = symptoms_cause
        this.symptoms_time = symptoms_time
    }

}