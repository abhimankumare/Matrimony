package com.example.matrimony.repository

import com.example.matrimony.model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {


    @Headers("Content-Type:application/json")
    @POST("signup")
    fun signUp(@Body info: SignUpModel): retrofit2.Call<SignUpResponse>


    @Headers("Content-Type:application/json")
    @POST("login")
    fun login(@Body info: Login): retrofit2.Call<LoginResponse>

    @Headers("Content-Type:application/json")
    @GET("master")
    fun getMasterData(): Call<MasterResponse>


    class RetrofitInstance {
        companion object {
            val BASE_URL: String = "http://anterpat.com/api/v1/"

            val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client: OkHttpClient = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
            }.build()
            fun getRetrofitInstance(): Retrofit {
                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
    }
}


