package com.beehealthy.digitalclininc.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.beehealthy.digitalclinic.apiservice.api.RepositoryServiceManager
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent
import com.beehealthy.digitalclininc.digitalcliniccanalytics.ProjectAnalytics
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


// Bind the dependency with Inject Hilt binding
@HiltViewModel
class EventListViewModel @Inject internal constructor(
    private val repositoryServiceManager: RepositoryServiceManager
) : ViewModel() {

    // TODO: inject the context in constructor when Hilt is fully ready
    fun getEvents(context: Application): LiveData<List<PatientEvent>> {

        object {}.javaClass.enclosingMethod?.name?.let {
            ProjectAnalytics.getInstance(context.applicationContext)
                .sendEvent(object {}.javaClass.enclosingClass.simpleName, it)
        }
        return repositoryServiceManager.getEvents()
    }

}