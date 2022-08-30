package com.venkat.digitalclinic.apiservice.api.contracts.requests

import androidx.lifecycle.LiveData
import com.venkat.digitalclinic.apiservice.models.DigitalClinic
import com.venkat.digitalclinic.apiservice.models.PatientEvent
import com.venkat.digitalclinic.apiservice.models.PatientPrescription
import com.venkat.digitalclinic.apiservice.models.ResponseObject

interface IPatientDetailsRepository {
    fun getEvents(): LiveData<ResponseObject<List<PatientEvent>>>
    fun getPrescriptions(): LiveData<ResponseObject<List<PatientPrescription>>>
    fun getDigitalClinicInfo(): LiveData<ResponseObject<DigitalClinic>>
}