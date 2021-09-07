package com.example.matrimony.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserProfileDetails {


    @SerializedName("gender")
    @Expose
    var gender: String? = null



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



}
