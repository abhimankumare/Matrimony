package com.example.matrimony.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProfileResponse {


    @SerializedName("user_data")
    @Expose
    var userDetails: UserDetails? = null


    @SerializedName("user_profile_data")
    @Expose
    var userprofileDetails: UserProfileDetails? = null


    @SerializedName("city_name")
    @Expose
    var cityDetails: MasterContent? = null

    @SerializedName("state_name")
    @Expose
    var stateDetails: MasterContent? = null

    @SerializedName("religion")
    @Expose
    var religionDetails: MasterContent? = null


    @SerializedName("caste_name")
    @Expose
    var casteDetails: MasterContent? = null

    @SerializedName("mother_tongue_name")
    @Expose
    lateinit var motherToungeDetails: List<MasterContent>



    @SerializedName("educations_name")
    @Expose
    var educationDetails: MasterContent? = null

    @SerializedName("occupation_name")
    @Expose
    var occuoatonDetails: MasterContent? = null

    @SerializedName("father_occupation_name")
    @Expose
    var fatheroccupatonDetails: MasterContent? = null

    @SerializedName("mother_occupation_name")
    @Expose
    var motheroccupatonDetails: MasterContent? = null


}
