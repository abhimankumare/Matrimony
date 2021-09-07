package com.example.matrimony.repository

import com.example.matrimony.model.*
import com.example.poultry_i.common.Utils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import java.io.IOException


interface ApiInterface {
    val ab: String?


    @POST("signup")
    fun signUp(@Body info: SignUpModel): retrofit2.Call<SignUpResponse>

   // @Headers({"Authorization", "Bearer "+ token})

    @POST("profilestore")
    fun RegisterBasicData(@Body info: BasicData): retrofit2.Call<SignUpResponse>


   // @Headers("Content-Type:application/json")
    @POST("login")
    fun login(@Body info: Login): retrofit2.Call<LoginResponse>

    //@Headers("Content-Type:application/json")
    @GET("master")
    fun getMasterData(): Call<MasterResponse>


    @GET("logout")
    fun LogoutData(): Call<LoginResponse>

    @POST("myprofile")
    fun ProfileData(): Call<ProfileResponse>

    @POST("password_change")
    fun ChanePasswordData(@Body info: PasswordInfo): Call<LoginResponse>


    class RetrofitInstance {
        companion object {
            val BASE_URL: String = "http://anterpat.com/api/v1/"

            val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

//            val client: OkHttpClient = OkHttpClient.Builder().apply {
//                this.addInterceptor(interceptor)
//            }.build()

            var client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val newRequest: Request = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "Bearer " + Utils.token)
                            .build()
                        return chain.proceed(newRequest)
                    }
                })
                .addInterceptor(interceptor).build()

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


