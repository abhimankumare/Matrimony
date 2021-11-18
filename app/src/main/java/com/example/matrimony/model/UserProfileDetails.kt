package com.example.matrimony.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserProfileDetails {


    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("user_type")
    @Expose
    var user_type: String? = null

    @SerializedName("user_bio")
    @Expose
    var user_bio: String? = null

    @SerializedName("educations_id")
    @Expose
    var educations_id: Int? = null

    @SerializedName("father_occupation_id")
    @Expose
    var father_occupation_id: Int? = null

    @SerializedName("mother_occupation_id")
    @Expose
    var mother_occupation_id: Int? = null

    @SerializedName("occupation_id")
    @Expose
    var occupation_id: Int? = null





    @SerializedName("religion_id")
    @Expose
    var religion_id: Int? = null

    @SerializedName("caste_id")
    @Expose
    var caste_id: Int? = null


    @SerializedName("language_id")
    @Expose
    var language_id: Int? = null


    @SerializedName("contry")
    @Expose
    var contry: String? = null

    @SerializedName("birth_date")
    @Expose
    var birth_date: String? = null


    @SerializedName("annual_income")
    @Expose
    var annual_income: String? = null

    @SerializedName("married_status")
    @Expose
    var married_status: String? = null

    @SerializedName("family_income")
    @Expose
    var family_income: String? = null

    @SerializedName("brother_no")
    @Expose
    var brother_no: String? = null

    @SerializedName("brother_married_no")
    @Expose
    var brother_married_no: String? = null

    @SerializedName("sister_no")
    @Expose
    var sister_no: String? = null

    @SerializedName("sister_married_no")
    @Expose
    var sister_married_no: String? = null

    @SerializedName("about_family")
    @Expose
    var about_family: String? = null

    @SerializedName("employed_sector")
    @Expose
    var employed_sector: String? = null

    @SerializedName("horoscope")
    @Expose
    var horoscope: String? = null


    @SerializedName("manglik")
    @Expose
    var manglik: String? = null



}
