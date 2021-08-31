package com.example.matrimony.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignUpResponse (

    val userdata: List<SignupContent>,
    val Status: String,
    val message: String,
    val token: String,
    @SerializedName("error")
    @Expose
    var error: LoginDetails? = null

)

class SignupContent (
    val id: String,
    val name: String,
    val mobile: String,
    val gender: String
)
