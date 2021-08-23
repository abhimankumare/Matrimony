package com.example.matrimony.repository

import com.example.matrimony.model.MasterResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {




    @Headers("Content-Type:application/json")
    @GET("state")
    fun getMasterPageData(): Call<MasterResponse>




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


