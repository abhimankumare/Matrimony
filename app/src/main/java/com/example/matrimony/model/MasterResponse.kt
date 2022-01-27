package com.example.matrimony.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class MasterResponse(
    val height: List<MasterContent>,
    val state: List<MasterContent>,
    val cities: List<MasterContent>,
    val education: List<MasterContent>,
    val occupation: List<MasterContent>,
    val religion: List<MasterContent>,
    val horoscope: List<MasterContent>,
    val employed_sector: List<MasterContent>,
    val income: List<MasterContent>,
    val mother_tongue: List<MasterContent>,
    val caste: List<MasterContent>,
    val Language: List<MasterContent>,

)


//class MasterResponse(
//
//
//    @SerializedName("id")
//    @Expose
//    var id: Int? = null,
//    @SerializedName("name")
//    @Expose
//    var name: String? = null,
//
//
//    @SerializedName("created_at")
//    @Expose
//    var created_at: String? = null,
//
//    @SerializedName("updated_at")
//    @Expose
//    var updated_at: String? = null
//)


