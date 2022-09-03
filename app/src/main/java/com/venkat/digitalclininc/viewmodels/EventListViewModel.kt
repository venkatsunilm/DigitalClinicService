package com.venkat.digitalclininc.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venkat.digitalclinic.apiservice.api.RepositoryServiceManager
import com.venkat.digitalclinic.apiservice.api.mockdata.EventsMockList
import com.venkat.digitalclinic.apiservice.helper.ResponseError
import com.venkat.digitalclinic.apiservice.models.PatientEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject internal constructor(
    private val repositoryServiceManager: RepositoryServiceManager
) : ViewModel() {

    private var _storageLiveData = MutableLiveData<List<PatientEvent>>()
    val storageLiveData: LiveData<List<PatientEvent>>
        get() = _storageLiveData

    fun getEvents() {
        viewModelScope.launch {
            try {
                _storageLiveData.value = repositoryServiceManager.getEvents()
            } catch (error: ResponseError) {
                // TODO: Update the UI with the error message
                // TODO: For now  the service is not available sending mock data back
                _storageLiveData.value = EventsMockList.getEventsMockList()
            }
        }
    }

}