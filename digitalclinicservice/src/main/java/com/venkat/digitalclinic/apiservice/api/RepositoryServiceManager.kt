package com.venkat.digitalclinic.apiservice.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.venkat.digitalclinic.apiservice.api.contracts.IPatientRepository
import com.venkat.digitalclinic.apiservice.api.contracts.IPatientDetailsRepository
import com.venkat.digitalclinic.apiservice.api.repository.PatientDetailsRepository
import com.venkat.digitalclinic.apiservice.api.repository.PatientRepository
import com.venkat.digitalclinic.apiservice.models.DigitalClinic
import com.venkat.digitalclinic.apiservice.models.PatientEvent
import com.venkat.digitalclinic.apiservice.models.PatientPrescription
import com.venkat.digitalclinic.apiservice.models.ResponseObject
import javax.inject.Inject

class RepositoryServiceManager @Inject constructor(
    private val patientRepository: PatientRepository,
    private val patientDetailsRepository: PatientDetailsRepository
) : IPatientRepository, IPatientDetailsRepository {

    override fun login(
        username: String,
        password: String
    ): MutableLiveData<ResponseObject<String>> {
        return patientRepository.login(username, password)
    }

    override suspend fun getEvents(): List<PatientEvent> {
        return patientDetailsRepository.getEvents()
    }

    override fun getPrescriptions(): LiveData<ResponseObject<List<PatientPrescription>>> {
        return patientDetailsRepository.getPrescriptions()
    }

    override fun getDigitalClinicInfo(): LiveData<ResponseObject<DigitalClinic>> {
        return patientDetailsRepository.getDigitalClinicInfo()
    }
}