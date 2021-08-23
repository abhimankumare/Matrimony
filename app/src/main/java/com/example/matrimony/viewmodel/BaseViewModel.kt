package com.triologic.android.downtodash.viewmodel


import androidx.lifecycle.ViewModel
import com.bfl.superapp.repository.CommonRepository

open class BaseViewModel : ViewModel() {

    fun getCommonRepo(): CommonRepository {
        return CommonRepository()
    }

}