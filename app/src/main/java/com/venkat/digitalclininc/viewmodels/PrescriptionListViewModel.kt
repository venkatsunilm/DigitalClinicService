package com.venkat.digitalclininc.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.venkat.digitalclinic.apiservice.api.RepositoryServiceManager
import com.venkat.digitalclinic.apiservice.models.PatientPrescription
import com.venkat.digitalclinic.apiservice.models.ResponseObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrescriptionListViewModel @Inject constructor(
    private val repositoryServiceManager: RepositoryServiceManager
) : ViewModel() {

    fun getPrescriptions(): LiveData<ResponseObject<List<PatientPrescription>>> {
        return repositoryServiceManager.getPrescriptions()
    }

}