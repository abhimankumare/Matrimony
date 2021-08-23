package com.bfl.superapp.viewmodel


import androidx.lifecycle.MutableLiveData
import com.example.matrimony.model.MasterResponse
import com.example.matrimony.model.SignUpModel
import com.example.matrimony.model.SignUpResponse

import com.triologic.android.downtodash.viewmodel.BaseViewModel
import retrofit2.http.Body


class CommonViewModel : BaseViewModel() {


    fun getMasterData(url: String): MutableLiveData<MasterResponse> {
        return getCommonRepo().getMasterDataResponse(url)

    }


    fun getSignUpData(obj: HashMap<String, String>): MutableLiveData<SignUpResponse> {
        return getCommonRepo().getSignUpResponse(obj)

    }


}