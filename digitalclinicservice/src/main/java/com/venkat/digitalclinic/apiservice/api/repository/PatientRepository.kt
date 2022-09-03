/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.venkat.digitalclinic.apiservice.api.repository

import com.venkat.digitalclinic.apiservice.helper.ResponseError
import com.venkat.digitalclinic.apiservice.helper.RetrofitClient
import com.venkat.digitalclinic.apiservice.models.ResponseObject
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class PatientRepository @Inject constructor() {
    private var userService: IPatientService =
        RetrofitClient.getInstance().create(IPatientService::class.java)

    suspend fun login(
        username: String,
        password: String
    ): String {
        try {
            val userCredentials = hashMapOf(USERNAME_KEY to username, PASSWORD_KEY to password)
            val jsonResponsePbj = userService.login(userCredentials)
            return jsonResponsePbj.get(TOKEN_KEY).asString
        } catch (error: Throwable) {
            throw ResponseError("login error", error)
        }
    }

    companion object {
        const val USERNAME_KEY = "username"
        const val PASSWORD_KEY = "password"
        const val TOKEN_KEY = "token"
    }

}