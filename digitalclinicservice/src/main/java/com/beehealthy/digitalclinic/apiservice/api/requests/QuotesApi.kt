package com.beehealthy.digitalclinic.apiservice.api.requests

import com.beehealthy.digitalclinic.apiservice.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET

//TODO: delete this class, added for testing purpose and experimental with third party API
sealed interface QuotesApi {
    @GET("/quotes")
    suspend fun getQuotes() : Response<QuoteList>
//    fun getQuotes(): Response<QuoteList>
}