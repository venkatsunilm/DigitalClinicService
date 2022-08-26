package com.beehealthy.digitalclinic.apiservice.api

import androidx.lifecycle.LiveData
import com.beehealthy.digitalclinic.apiservice.api.requests.IManager
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent
import javax.inject.Inject
import javax.inject.Singleton

// TODO: Instead of injecting all the services in future here
//  Create a single contract for all services
class RepositoryServiceManager @Inject constructor(
    private val eventsService: EventsService
) : IManager {

    override fun initialize() {
    }

    override fun getEvents(): LiveData<List<PatientEvent>> {
        return eventsService.getEvents()
    }

    override fun getPrescriptions() {
    }

    override fun getVaccinations() {
    }

    // TODO: This singleton instance might be required only if we want to create a manual ViewModelFactory
    //  as centralized and manual DI. Remove this in future if we depend on the Hilt DI which is experimental
    companion object {
        @Volatile
        private var instance: RepositoryServiceManager? = null

        fun getInstance(eventsService: EventsService) =
            instance ?: synchronized(this) {
                instance ?: RepositoryServiceManager(eventsService).also { instance = it }
            }
    }

}