/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.beehealthy.digitalclinic.apiservice.api.repository

import androidx.lifecycle.MutableLiveData
import com.beehealthy.digitalclinic.apiservice.api.contracts.IUserRepository
import com.beehealthy.digitalclinic.apiservice.api.contracts.requests.IPatientService
import com.beehealthy.digitalclinic.apiservice.helper.RetrofitClient
import com.beehealthy.digitalclinic.apiservice.models.ResponseObject
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//@Singleton
class PatientRepository @Inject constructor() : IUserRepository {
    private var userService: IPatientService =
        RetrofitClient.getInstance().build().create(IPatientService::class.java)

    override fun login(
        username: String,
        password: String
    ): MutableLiveData<ResponseObject<String>> {
        val responseData: MutableLiveData<ResponseObject<String>> = MutableLiveData()
        val userCredentials = hashMapOf(USERNAME_KEY to username, PASSWORD_KEY to password)
        userService.login(userCredentials).enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                responseData.postValue(ResponseObject(null, t.message))
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    responseData.postValue(
                        ResponseObject(
                            response.body()?.get(TOKEN_KEY)?.asString,
                            statusCode = response.code()
                        )
                    )
                } else {
                    responseData.postValue(
                        ResponseObject(
                            null,
                            response.message(),
                            response.code()
                        )
                    )
                }
            }
        })
        return responseData
    }

    companion object {
        const val USERNAME_KEY = "username"
        const val PASSWORD_KEY = "password"
        const val TOKEN_KEY = "token"
        private var patientRepository: PatientRepository? = null
        fun getInstance(): PatientRepository {
            if (patientRepository == null) {
                patientRepository = PatientRepository()
            }
            return patientRepository!!
        }
    }
}