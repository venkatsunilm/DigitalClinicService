package com.venkat.digitalclinic.apiservice.models

import java.util.*

data class PatientDetail(
    val patientId: UUID,
    val givenName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val address: String,  // a new data class to segregate address, etc...
)

data class PatientEvent(
    val eventId: String, // UUID or GUID
    val patientFirstName: String,
    val ValidityDate: String,
    val referenceImageUrl: String,
    val referenceHeaderType: String,
    val referenceSubHeaderType: String,
    val referenceNotes: List<Instructions>,
    val doctorFullName: String,
    val doctorSpecialization: String,
    val writtenDate: String,
)

data class Instructions(
    val detail: String
)

data class PatientPrescription(
    val prescriptionId: String,
    val patientFirstName: String,
    val patientEndDate: String,
    val referenceImageUrl: String,
    val medicineName: String,
    val medicineDetail: String,
    val medicineUsageInstructions: List<Instructions>,
    val referenceHeaderType: String,
    val referenceSubHeaderType: String,
    val doctorFullName: String,
    val doctorSpecialization: String,
    val writtenDate: String,
    )

data class DigitalClinic(
    val digitalClinicId: UUID,
    val patientFirstName: String,
    val digitalDescription: String,
    val newDiscussion: NewDiscussions,
    val closedDiscussion: ClosedDiscussions
)

data class NewDiscussions(
    val newDiscussionId: UUID,
    val newDiscussionDetails: List<NewDiscussionDetail>
)

data class ClosedDiscussions(
    val closedDiscussionId: UUID,
    val closedDiscussionDetails: List<ClosedDiscussionDetail>
)

data class NewDiscussionDetail(
    val referenceImageUrl: String,
    val Information: String,
    val paymentInfo: String
)

data class ClosedDiscussionDetail(
    val referenceImageUrl: String,
    val header: String,
    val subHeader: String,
    val patientName: String,
    val endDate: String
)