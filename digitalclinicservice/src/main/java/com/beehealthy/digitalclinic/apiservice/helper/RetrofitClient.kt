/*
 * Copyright (c) 2021 F-secure Corporation.
 */

package com.beehealthy.digitalclinic.apiservice.helper

import com.beehealthy.digitalclinic.apiservice.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class RetrofitClient {
    companion object {
        private var retrofit: Retrofit? = null
        // TODO: Create and provide a base URL for Digital clinic
        private const val BASE_URL =  "https://quotable.io/"

        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun getInstance(authToken: String? = null): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                    .client(OkHttpClient.Builder()
//                        .addInterceptor { chain ->
//                            chain.proceed((chain.request().newBuilder().also {
//                                it.addHeader("Authorization", "Bearer $authToken")
//                            }.build()))
//                        }.also { client ->
//                            if (BuildConfig.DEBUG) {
//                                val logger = HttpLoggingInterceptor().apply {
//                                    level = HttpLoggingInterceptor.Level.BODY
//                                }
//                                client.addInterceptor(logger)
//                            }
//                        }
//                        .build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit!!
        }
    }
}