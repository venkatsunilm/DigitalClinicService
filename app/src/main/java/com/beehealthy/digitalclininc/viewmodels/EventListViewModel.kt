package com.beehealthy.digitalclininc.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.beehealthy.digitalclinic.apiservice.api.RepositoryServiceManager
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent
import com.beehealthy.digitalclinic.apiservice.models.ResponseObject
import com.beehealthy.digitalclininc.constants.ApplicationConstants
import com.beehealthy.digitalclininc.digitalcliniccanalytics.ProjectAnalytics
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject internal constructor(
    private val repositoryServiceManager: RepositoryServiceManager
) : ViewModel() {

    fun getEvents(): LiveData<ResponseObject<List<PatientEvent>>> {
        object {}.javaClass.enclosingMethod?.name?.let {
            ProjectAnalytics.getInstance(ApplicationConstants.applicationContext)
                .sendEvent(object {}.javaClass.enclosingClass.simpleName, it)
        }
        return repositoryServiceManager.getEvents()
    }

}