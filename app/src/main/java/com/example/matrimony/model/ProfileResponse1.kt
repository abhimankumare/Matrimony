package com.example.matrimony.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProfileResponse1 {

    @SerializedName("user_data")
    @Expose
    var userDetails: UserDetails? = null

}
