package com.example.matrimony.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class LoginResponse {
    @SerializedName("status")
    @Expose
    var Status: String? = null


    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("error")
    @Expose
    var error: String? = null

    @SerializedName("userdata")
    @Expose
    var loginDetails: LoginDetails? = null

    @SerializedName("token")
    @Expose
    var token: String? = null


}
