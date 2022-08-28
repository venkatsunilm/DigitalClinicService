package com.beehealthy.digitalclinic.apiservice.api.contracts.requests

import com.beehealthy.digitalclinic.apiservice.models.DigitalClinic
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent
import com.beehealthy.digitalclinic.apiservice.models.PatientPrescription
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface IPatientDetailsService {
    @GET("v1/events")
    fun getEvents(
        @Header("Content-Type") contentType: String?,
        @Header("Authorization") token: String,
    ): Call<List<PatientEvent>>

    @GET("v1/prescriptions")
    fun getPrescriptions(
        @Header("Content-Type") contentType: String?,
        @Header("Authorization") token: String,
    ): Call<List<PatientPrescription>>

    @GET("v1/vaccinations")
    fun getDigitalClinic(
        @Header("Content-Type") contentType: String?,
        @Header("Authorization") token: String,
    ): Call<DigitalClinic>

}