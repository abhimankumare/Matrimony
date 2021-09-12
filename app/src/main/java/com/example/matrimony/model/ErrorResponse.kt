package com.example.matrimony.model

import com.google.gson.annotations.SerializedName

class ErrorResponse {
    @SerializedName("message")
    val message: String? = null

    @SerializedName("error")
    val error: Error? = null
}
