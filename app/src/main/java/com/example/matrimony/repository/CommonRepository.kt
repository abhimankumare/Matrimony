package com.bfl.superapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.matrimony.model.MasterResponse
import com.example.matrimony.model.SignUpModel
import com.example.matrimony.model.SignUpResponse
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException


class CommonRepository : BaseRepository() {

    fun getMasterDataResponse(url:String) : MutableLiveData<MasterResponse> {
//        Log.e("work","API"+ApiName.BiometricsURL)
        Log.e("work","API"+url)
        val data = MutableLiveData<MasterResponse>()
        getNetworkService()
            .getHybridFaqResponseData(url)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response: MasterResponse? ->
                Log.e("work",  " RESPONSE"+
                        Gson().toJson(response))
                if (response != null) {
                    data.postValue(response)
                }
            }, { error: Throwable? ->
                error?.printStackTrace()
                var msg = error?.message
                Log.e("work","error ------"+msg)
                if (error is HttpException) {
                    msg = getError(error)
                }
                data.postValue(
                    MasterResponse(emptyList(),emptyList(),emptyList(),emptyList(),emptyList(),emptyList())
                )
            })
        return data
    }

    fun getSignUpResponse(obj: HashMap<String, String>) : MutableLiveData<SignUpResponse> {
//        Log.e("work","API"+ApiName.BiometricsURL)
   //     Log.e("work","API"+url)
        val data = MutableLiveData<SignUpResponse>()
        getNetworkService()
            .registration(obj)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response: SignUpResponse? ->
                Log.e("work",  " RESPONSE"+
                        Gson().toJson(response))
                if (response != null) {
                    data.postValue(response)
                }
            }, { error: Throwable? ->
                error?.printStackTrace()
                var msg = error?.message
                Log.e("work","error ------"+msg)
                if (error is HttpException) {
                    msg = getError(error)
                }
                data.postValue(
                    SignUpResponse(emptyList(),"","")
                )
            })
        return data
    }



}


