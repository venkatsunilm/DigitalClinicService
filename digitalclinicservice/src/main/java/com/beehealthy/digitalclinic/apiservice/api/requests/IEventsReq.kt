package com.beehealthy.digitalclinic.apiservice.api.requests

import androidx.lifecycle.LiveData
import com.beehealthy.digitalclinic.apiservice.api.EventsService
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent

interface IEventsReq {
    fun getEvents(): LiveData<List<PatientEvent>>
    fun getPrescriptions()
    fun getVaccinations()
}