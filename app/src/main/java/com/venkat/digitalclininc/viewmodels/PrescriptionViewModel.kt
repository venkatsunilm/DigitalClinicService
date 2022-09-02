package com.venkat.digitalclininc.viewmodels

import androidx.lifecycle.ViewModel
import com.venkat.digitalclinic.apiservice.models.PatientPrescription
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class PrescriptionViewModel(item: PatientPrescription) : ViewModel() {
    val validityDate = item.patientEndDate
    val patientName = item.patientFirstName
    val referralType = item.referenceHeaderType
    val doctorName = item.doctorFullName
    val writtenDate = item.writtenDate
}