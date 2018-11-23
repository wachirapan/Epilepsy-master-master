package com.codehousedev.data

class ProfileData {
    var profile_id: Int = 0
    var profile_name: String? = null
    var profile_age: String? = null
    var profile_tel: String? = null
    var profile_treatment: String? = null
    var profile_cause: String? = null
    var profile_type: String? = null
    var profile_stimulant: String? = null
    var profile_sideeffect: String? = null
    var profile_allergyhistory: String? = null

    constructor(profile_id: Int, profile_name: String, profile_age: String, profile_tel: String,
                profile_treatment: String, profile_cause: String, profile_type: String, profile_stimulant: String,
                profile_sideeffect: String, profile_allergyhistory: String) {
        this.profile_id = profile_id
        this.profile_name = profile_name
        this.profile_age = profile_age
        this.profile_tel = profile_tel
        this.profile_treatment = profile_treatment
        this.profile_cause = profile_cause
        this.profile_type = profile_type
        this.profile_stimulant = profile_stimulant
        this.profile_sideeffect = profile_sideeffect
        this.profile_allergyhistory = profile_allergyhistory
    }
    constructor(profile_name: String, profile_age: String, profile_tel: String,
                profile_treatment: String, profile_cause: String, profile_type: String, profile_stimulant: String,
                profile_sideeffect: String, profile_allergyhistory: String){
        this.profile_name = profile_name
        this.profile_age = profile_age
        this.profile_tel = profile_tel
        this.profile_treatment = profile_treatment
        this.profile_cause = profile_cause
        this.profile_type = profile_type
        this.profile_stimulant = profile_stimulant
        this.profile_sideeffect = profile_sideeffect
        this.profile_allergyhistory = profile_allergyhistory
    }
}