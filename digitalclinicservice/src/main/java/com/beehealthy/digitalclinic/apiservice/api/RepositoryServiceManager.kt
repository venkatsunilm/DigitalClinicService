package com.beehealthy.digitalclinic.apiservice.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beehealthy.digitalclinic.apiservice.api.contracts.requests.IRepositoryServiceManager
import com.beehealthy.digitalclinic.apiservice.api.repository.PatientDetailsRepository
import com.beehealthy.digitalclinic.apiservice.api.repository.PatientRepository
import com.beehealthy.digitalclinic.apiservice.models.DigitalClinic
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent
import com.beehealthy.digitalclinic.apiservice.models.PatientPrescription
import com.beehealthy.digitalclinic.apiservice.models.ResponseObject
import javax.inject.Inject
import javax.inject.Singleton

// TODO: Instead of injecting all the services in future here
//  Create a single contract for all services
class RepositoryServiceManager @Inject constructor(
    private val patientRepository: PatientRepository,
    private val patientDetailsRepository: PatientDetailsRepository
) : IRepositoryServiceManager {

    override fun initialize() {
    }

    override fun login(
        username: String,
        password: String
    ): MutableLiveData<ResponseObject<String>> {
        return patientRepository.login(username, password)
    }

    override fun getEvents(): LiveData<ResponseObject<List<PatientEvent>>> {
        return patientDetailsRepository.getEvents()
    }

    override fun getPrescriptions(): LiveData<ResponseObject<List<PatientPrescription>>> {
       return patientDetailsRepository.getPrescriptions()
    }

    override fun getDigitalClinicInfo(): LiveData<ResponseObject<DigitalClinic>> {
        return patientDetailsRepository.getDigitalClinicInfo()
    }

    companion object {
        @Volatile
        private var instance: RepositoryServiceManager? = null

        fun getInstance(
            patientRepository: PatientRepository,
            patientDetailsRepository: PatientDetailsRepository
        ) = instance ?: synchronized(this) {
            instance ?: RepositoryServiceManager(
                PatientRepository.getInstance(),
                patientDetailsRepository
            ).also { instance = it }
        }
    }
}