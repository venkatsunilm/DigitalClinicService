package com.beehealthy.digitalclinic.apiservice.api.mockdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beehealthy.digitalclinic.apiservice.api.EventsService
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent

// TODO: implement room dependencies
// annotate this class as @Dao
// Pass this class as dependency to the RepositoryServiceManager
sealed interface FakeEventDao {
    fun getEvents()
}