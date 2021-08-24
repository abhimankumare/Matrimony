package com.bfl.superapp.api



import com.example.matrimony.model.MasterResponse
import com.example.matrimony.model.SignUpModel
import com.example.matrimony.model.SignUpResponse
import io.reactivex.Observable
import retrofit2.Callback
import retrofit2.http.*


interface NetworkService {


    @Headers("Content-Type: application/json")
    @GET
    fun getHybridFaqResponseData(@Url url:String?):Observable<MasterResponse>

    @Headers("Content-Type: application/json")
    @POST("/signup")
    fun registration(@Body obj: HashMap<String, String>): Observable<SignUpResponse>

}
