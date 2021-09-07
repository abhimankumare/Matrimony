package com.example.matrimony.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class UserDetails {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("mobile")
    @Expose
    var mobile: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

}
