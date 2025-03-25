package com.wpay.medibook.data.model

sealed class MedicalRequestEvent {
    data object RequestPrescription : MedicalRequestEvent()
    data object PrescriptionFormClicked : MedicalRequestEvent()
    data object RequestConsultation : MedicalRequestEvent()
    data object ConsultationFormClicked : MedicalRequestEvent()
    data object CancelRequest : MedicalRequestEvent()
    data object OpenDatePicker : MedicalRequestEvent()
    data class DateSelected (val date: String) : MedicalRequestEvent()
    data class SpecialistSelected (val name: String) : MedicalRequestEvent()
    data class MedicalCenterSelected  (val name: String) : MedicalRequestEvent()
}