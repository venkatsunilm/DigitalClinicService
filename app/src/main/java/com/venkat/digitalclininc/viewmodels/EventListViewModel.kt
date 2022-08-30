package com.venkat.digitalclininc.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.venkat.digitalclinic.apiservice.api.RepositoryServiceManager
import com.venkat.digitalclinic.apiservice.models.PatientEvent
import com.venkat.digitalclinic.apiservice.models.ResponseObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject internal constructor(
    private val repositoryServiceManager: RepositoryServiceManager
) : ViewModel() {

    fun getEvents(): LiveData<ResponseObject<List<PatientEvent>>> {
        return repositoryServiceManager.getEvents()
    }

}