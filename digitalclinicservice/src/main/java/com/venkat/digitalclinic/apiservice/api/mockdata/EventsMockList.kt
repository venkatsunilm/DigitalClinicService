package com.venkat.digitalclinic.apiservice.api.mockdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.venkat.digitalclinic.apiservice.models.Instructions
import com.venkat.digitalclinic.apiservice.models.PatientEvent
import com.venkat.digitalclinic.apiservice.models.PatientPrescription

// Made this class as singleton to know the usage
object EventsMockList {


    fun getEventsMockList() : LiveData<List<PatientEvent>> {

        val eventList = mutableListOf<PatientEvent>()
        val events = MutableLiveData<List<PatientEvent>>()

        eventList.add(PatientEvent(
            "1",
            "Venkat",
            "Valid 1 year 2 months",
            "https://source.unsplash.com/user/c_v_r/1900x800",
            "Laboratory referral",
            "Laboratory referral sub header",
            listOf(
                Instructions("B-Perusverenkuva plus tromb"),
                Instructions("S-Tyreotropiini"),
                Instructions("S-Tyroksiini, vapaa")
            ),
            "Mari Kiekara",
            "Lab specialist",
            "Aug 21, 2022"
        ))
        eventList.add(PatientEvent(
            "2",
            "Venkat",
            "valid 10 months",
            "https://source.unsplash.com/user/c_v_r/1900x800",
            "referral",
            "referral sub header",
            listOf(
                Instructions("Suuhygienistin lahete"),
                Instructions("1/1-3 Kayntia"),
            ),
            "Hammaslaakari Satkauskiene",
            "Dental specialist",
            "Aug 21, 2021"
        ))
        eventList.add(PatientEvent(
            "3",
            "Venkat",
            "22 Aug, 2019",
            "https://source.unsplash.com/user/c_v_r/1900x800",
            "referral",
            "referral sub header",
            listOf(
                Instructions("Suuhygienistin lahete"),
                Instructions("1/1-3 Kayntia"),
            ),
            "Satkauskiene",
            "Eye specialist",
            "Jan 21, 2019"
        ))

        eventList.add(PatientEvent(
            "4",
            "Venkat",
            "22 Feb, 2018",
            "https://source.unsplash.com/user/c_v_r/1900x800",
            "referral",
            "referral sub header",
            listOf(
                Instructions("Suuhygienistin lahete"),
                Instructions("1/1-3 Kayntia"),
            ),
            "Hammaslaakari",
            "Bone specialist",
            "05 Jan, 2018"
        ))


        events.value = eventList

        return events
    }
    fun getPrescriptionMockList() : LiveData<List<PatientPrescription>> {

        val eventList = mutableListOf<PatientPrescription>()
        val events = MutableLiveData<List<PatientPrescription>>()

        eventList.add(PatientPrescription(
            "1",
            "Venkat",
            "Valid 1 year 2 months",
            "https://source.unsplash.com/user/c_v_r/1900x800",
            "Laboratory referral",
            "Laboratory referral sub header",
            listOf(
                Instructions("B-Perusverenkuva plus tromb"),
                Instructions("S-Tyreotropiini"),
                Instructions("S-Tyroksiini, vapaa")
            ),
            "Mari Kiekara",
            "Lab specialist",
            "Mikko",
            "Expert",
            "Aug 21, 2022",
            ))
        eventList.add(PatientPrescription(
            "2",
            "Venkat",
            "valid 10 months",
            "https://source.unsplash.com/user/c_v_r/1900x800",
            "referral",
            "referral sub header",
            listOf(
                Instructions("Suuhygienistin lahete"),
                Instructions("1/1-3 Kayntia"),
            ),
            "Hammaslaakari Satkauskiene",
            "Dental specialist",
            "Mikko",
            "Specialist",
            "Aug 21, 2021"
        ))
        eventList.add(PatientPrescription(
            "3",
            "Venkat",
            "22 Aug, 2019",
            "https://source.unsplash.com/user/c_v_r/1900x800",
            "referral",
            "referral sub header",
            listOf(
                Instructions("Suuhygienistin lahete"),
                Instructions("1/1-3 Kayntia"),
            ),
            "Satkauskiene",
            "Eye specialist",
            "Mikko",
            "Expert",
            "Jan 21, 2019"
        ))

        eventList.add(PatientPrescription(
            "4",
            "Venkat",
            "22 Feb, 2018",
            "https://source.unsplash.com/user/c_v_r/1900x800",
            "referral",
            "referral sub header",
            listOf(
                Instructions("Suuhygienistin lahete"),
                Instructions("1/1-3 Kayntia"),
            ),
            "Hammaslaakari",
            "Bone specialist",
            "Mikko",
            "Specialist",
            "05 Jan, 2018"
        ))


        events.value = eventList

        return events
    }

}