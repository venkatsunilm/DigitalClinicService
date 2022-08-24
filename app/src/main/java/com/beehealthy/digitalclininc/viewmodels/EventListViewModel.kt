package com.beehealthy.digitalclininc.viewmodels

import androidx.lifecycle.ViewModel
import com.beehealthy.digitalclinic.apiservice.api.RepositoryServiceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// Bind the dependency with Inject Hilt binding
@HiltViewModel
class EventListViewModel @Inject internal constructor(
    private val repositoryServiceManager: RepositoryServiceManager
) : ViewModel() {
    fun getEvents() = repositoryServiceManager.getEvents()
}