package com.venkat.digitalclininc.viewmodels

import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.venkat.digitalclinic.apiservice.api.RepositoryServiceManager
import com.venkat.digitalclinic.apiservice.api.mockdata.EventsMockList
import com.venkat.digitalclinic.apiservice.models.PatientEvent
import com.venkat.digitalclininc.adapter.EventAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject internal constructor(
    private val repositoryServiceManager: RepositoryServiceManager
) : ViewModel() {

    init {
        getEvents()
    }

    fun getEvents(): LiveData<List<PatientEvent>> {
        var storageLiveData: LiveData<List<PatientEvent>> =
            MutableLiveData()
        var retrievedTodo: List<PatientEvent>? = listOf()
        viewModelScope.launch {
            storageLiveData = liveData() {
                try {
//                    repositoryServiceManager.getEvents()
                    // TODO: For now as the service is not available
                    //  sending mock data back
                    retrievedTodo = EventsMockList.getEventsMockList().value
                } catch (error: Error) {
                    // TODO: Update the UI with the error message to the subscribers
                } finally {
                    retrievedTodo?.let { emit(it) }
                }
            }
        }
        return storageLiveData
    }

}