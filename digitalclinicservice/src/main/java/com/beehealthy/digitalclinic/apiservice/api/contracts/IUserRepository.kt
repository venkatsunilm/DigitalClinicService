package com.beehealthy.digitalclinic.apiservice.api.contracts

import androidx.lifecycle.MutableLiveData
import com.beehealthy.digitalclinic.apiservice.models.ResponseObject

interface IUserRepository {
    fun login(username: String, password: String): MutableLiveData<ResponseObject<String>>
}