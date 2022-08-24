package com.beehealthy.digitalclininc.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject internal constructor(): ViewModel() {
    var validityDate = "Validity Date"
    var patientName = "Patient Name"
    var referralType = "Referral type"
    var doctorName = "Doctor Name"
    var writtenDate = "Written Date"
}