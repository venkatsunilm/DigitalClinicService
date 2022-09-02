package com.venkat.digitalclinic.apiservice.api.repository

import com.venkat.digitalclinic.apiservice.models.DigitalClinic
import com.venkat.digitalclinic.apiservice.models.PatientEvent
import com.venkat.digitalclinic.apiservice.models.PatientPrescription
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface IPatientDetailsService {
    @GET("v1/events")
    suspend fun getEvents(
        @Header("Authorization") token: String,
    ): List<PatientEvent>

    @GET("v1/prescriptions")
    fun getPrescriptions(
        @Header("Authorization") token: String,
    ): Call<List<PatientPrescription>>

    @GET("v1/vaccinations")
    fun getDigitalClinic(
        @Header("Authorization") token: String,
    ): Call<DigitalClinic>

}