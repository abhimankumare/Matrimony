package com.example.matrimony.model


data class SignUpModel (val user_type: String, val name: String,
                        val gender: String, val birth_date: String,
                        val hight: String, val contry: String,
                        val state_id: String, val city_id: String,
                        val mobile: String, val user_bio: String,
                        val password: String, val confirm_password: String)




    //        @Field("name") name: String?,
//        @Field("gender") gender: String?,
//        @Field("birth_date") birth_date: String?,
//        @Field("hight") hight: String?,
//        @Field("contry") contry: String?,
//        @Field("state_id") state_id: String?,
//        @Field("city_id") city_id: String?,
//        @Field("mobile") mobile: String?,
//        @Field("user_bio") user_bio: String?,
//        @Field("password") password: String?,
//        @Field("confirm_password") confirm_password: String?,


   /* @SerializedName("user_type")
    @Expose
    private var user_type: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("gender")
    @Expose
    private var gender: String? = null

    @SerializedName("birth_date")
    @Expose
    private var birth_date: String? = null

    @SerializedName("contry")
    @Expose
    private var contry: String? = null



    @SerializedName("state_id")
    @Expose
    private var state_id: String? = null

    @SerializedName("city_id")
    @Expose
    private var city_id: String? = null


    @SerializedName("mobile")
    @Expose
    private var mobile: String? = null


    @SerializedName("user_bio")
    @Expose
    private var user_bio: String? = null

    @SerializedName("password")
    @Expose
    private var password: String? = null


    @SerializedName("confirm_password")
    @Expose
    private var confirm_password: String? = null
*/


