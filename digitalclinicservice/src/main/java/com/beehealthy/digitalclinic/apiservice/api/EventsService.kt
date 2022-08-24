package com.beehealthy.digitalclinic.apiservice.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beehealthy.digitalclinic.apiservice.api.mockdata.EventsMockList
import com.beehealthy.digitalclinic.apiservice.api.requests.IEventsReq
import com.beehealthy.digitalclinic.apiservice.api.requests.QuotesApi
import com.beehealthy.digitalclinic.apiservice.helper.RetrofitHelper
import com.beehealthy.digitalclinic.apiservice.models.PatientEvent
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(DelicateCoroutinesApi::class)
@Singleton
class EventsService @Inject constructor() : IEventsReq {

    // TODO: example url, not working now, replace when the service API is availble
    private val eventsUrl = "https://digitalservice.com/patientUUID/events"

    // TODO: This is sample to try using coroutines, delete this later
    init {
        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = quotesApi.getQuotes()
            Log.d("Venkat: ", result.body().toString())
        }
    }

    override fun getEvents(): LiveData<List<PatientEvent>> {
        val resultToReturn = MutableLiveData<List<PatientEvent>>()
        var resultList = ArrayList<PatientEvent>() // As of now not used, passing back mock data.
        val url = URL(eventsUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            // TODO: Use Retrofit or any to fetch the response from service
            // Prepare a mock list and send back for now to test

            // RxKotlin Observable, using just because i have the data already here getEventsMockList
            val resultResponse: Observable<LiveData<List<PatientEvent>>> =
                Observable.just(EventsMockList.getEventsMockList())

            // TODO: Trying out the operators here, but as per this use case
            // Remove the unnecessary operators once the API service is available
            resultResponse
                .filter { it.value?.size!! > 0 }
                .repeat(1) // used to get more data for testing
//                .delay(100, TimeUnit.MILLISECONDS)
                .throttleFirst(5, TimeUnit.SECONDS)
                .subscribeBy(
                    onNext = { liveData ->
//                        liveData.value.also {
//                            if (it != null) {
//                                resultList = it.toList() as ArrayList<PatientEvent>
//                            }
//                        }

//                        val mutListIterator = liveData.value?.listIterator()
//                        if (mutListIterator != null) {
//                            while(mutListIterator.hasNext()){
//                                print("Venkat mutListIterator.next()")
//                                resultToReturn.value?.toMutableList()?.add(mutListIterator.next())
//                            }
//                        }

                        // TODO: we can also return the readonly list commented the code above,
                        //  but for now returning the mock data Live data EventsMockList.getEventsMockList()
                        resultToReturn.value = liveData.value
                    },
                    onError = {
                        it.printStackTrace()
                    },
                    onComplete = {
                    }
                )
            return resultToReturn
        }

        // return Nothing, for now returning empty object
        return MutableLiveData()
    }

    override fun getPrescriptions() {
    }

    override fun getVaccinations() {
    }
}