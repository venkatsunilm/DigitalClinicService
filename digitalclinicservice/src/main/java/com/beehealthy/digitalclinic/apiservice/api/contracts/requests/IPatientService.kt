/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.beehealthy.digitalclinic.apiservice.api.contracts.requests

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

internal interface IPatientService {
    @POST("v1/login")
    fun login(@Body body: HashMap<String, String>): Call<JsonObject>
}