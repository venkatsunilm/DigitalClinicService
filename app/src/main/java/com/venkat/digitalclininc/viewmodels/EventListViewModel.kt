package com.venkat.digitalclininc.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venkat.digitalclinic.apiservice.api.RepositoryServiceManager
import com.venkat.digitalclinic.apiservice.api.mockdata.EventsMockList
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
                // _storageLiveData.value = repositoryServiceManager.getEvents()
                // TODO: For now  the service is not available
                //  sending mock data back
                _storageLiveData.value = EventsMockList.getEventsMockList()
            } catch (error: Error) {
                // TODO: Update the UI with the error message to the subscribers
                error.message?.let { Log.d(ContentValues.TAG, it) }
                Log.d(ContentValues.TAG, error.stackTrace.toString())
            }
        }
    }

}