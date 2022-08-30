package com.venkat.digitalclinic.apiservice.api.contracts

import androidx.lifecycle.MutableLiveData
import com.venkat.digitalclinic.apiservice.models.ResponseObject

interface IUserRepository {
    fun login(username: String, password: String): MutableLiveData<ResponseObject<String>>
}