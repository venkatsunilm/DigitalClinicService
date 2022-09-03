/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.venkat.digitalclinic.apiservice.api.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.venkat.digitalclinic.apiservice.api.contracts.IPatientRepository
import com.venkat.digitalclinic.apiservice.helper.RetrofitClient
import com.venkat.digitalclinic.apiservice.models.ResponseObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PatientRepository @Inject constructor() : IPatientRepository {
    private var userService: IPatientService =
        RetrofitClient.getInstance().create(IPatientService::class.java)

    override fun login(
        username: String,
        password: String
    ): MutableLiveData<ResponseObject<String>> {
        val responseData: MutableLiveData<ResponseObject<String>> = MutableLiveData()
        val userCredentials = hashMapOf(USERNAME_KEY to username, PASSWORD_KEY to password)
        userService.login(userCredentials).enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                // TODO: remove this once the API service is ready
                val token = "osnvosvojnonvojnsdojcnojncjnsojdncojsncojsnjdn"
                responseData.postValue(ResponseObject(token, t.message))
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
    }
}