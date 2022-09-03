package com.venkat.digitalclininc.viewmodels

import androidx.lifecycle.ViewModel
import com.venkat.digitalclinic.apiservice.models.PatientEvent

class EventViewModel (item: PatientEvent) : ViewModel() {
    val validityDate = item.ValidityDate
    val patientName = item.patientFirstName
    val referralType = item.referenceHeaderType
    val doctorName = item.doctorFullName
    val writtenDate = item.writtenDate
}