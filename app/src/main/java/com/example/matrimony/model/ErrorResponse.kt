package com.example.matrimony.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorRespone(
    @SerializedName("type")
    @Expose val type: Int = 0,
    @SerializedName("error")
    @Expose val error: Error = Error()
) {
    data class Error(
        @SerializedName("0")
        @Expose val errorStr: List<String>? = arrayListOf()
    )
}


