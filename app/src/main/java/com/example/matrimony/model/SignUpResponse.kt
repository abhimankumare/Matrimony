package com.example.matrimony.model

class SignUpResponse (

    val userdata: List<SignupContent>,
    val Status: String,
    val message: String
)

class SignupContent (
    val id: String,
    val name: String,
    val mobile: String,
    val gender: String
)
