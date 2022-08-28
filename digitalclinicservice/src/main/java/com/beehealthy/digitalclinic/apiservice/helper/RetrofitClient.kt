/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.beehealthy.digitalclinic.apiservice.helper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

class RetrofitClient {
    companion object {
        private var retrofitBuilder: Retrofit.Builder? = null

        // TODO: Create and provide a base URL for Digital clinic
        private const val BASE_URL = "https://quotable.io/"

        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun getInstance(): Retrofit.Builder {
            if (retrofitBuilder == null) {
                retrofitBuilder = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
            }
            return retrofitBuilder!!
        }
    }
}