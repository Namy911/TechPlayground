package com.wpay.medibook.data.model

sealed class MedicalRequestEvent {
    data object RequestPrescription : MedicalRequestEvent()
    data object PrescriptionFormClicked : MedicalRequestEvent()
    data object RequestConsultation : MedicalRequestEvent()
    data object ConsultationFormClicked : MedicalRequestEvent()
    data object CancelRequest : MedicalRequestEvent()
    data object OpenDatePicker : MedicalRequestEvent()
    data class DateSelected(val date: String) : MedicalRequestEvent()
    data class SpecialistNameChanged(val name: String, val requestId: String) :
        MedicalRequestEvent()

    data class MedicalCenterChanged(val name: String) : MedicalRequestEvent()
}