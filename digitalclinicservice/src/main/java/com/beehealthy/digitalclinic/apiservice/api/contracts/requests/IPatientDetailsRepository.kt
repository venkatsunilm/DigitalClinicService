package com.beehealthy.digitalclinic.apiservice.api.contracts.requests

import androidx.lifecycle.LiveData
import com.beehealthy.digitalclinic.apiservice.models.DigitalClinic
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent
import com.beehealthy.digitalclinic.apiservice.models.PatientPrescription
import com.beehealthy.digitalclinic.apiservice.models.ResponseObject

interface IPatientDetailsRepository {
    fun getEvents(): LiveData<ResponseObject<List<PatientEvent>>>
    fun getPrescriptions(): LiveData<ResponseObject<List<PatientPrescription>>>
    fun getDigitalClinicInfo(): LiveData<ResponseObject<DigitalClinic>>
}