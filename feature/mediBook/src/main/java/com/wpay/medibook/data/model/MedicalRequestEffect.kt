package com.wpay.medibook.data.model

sealed class MedicalRequestEffect {
    data class ShowRequestSuccess(val requestId: String) : MedicalRequestEffect()
    data object ShowDatePicker : MedicalRequestEffect()
    data object CloseDatePicker : MedicalRequestEffect()
    data object NavigateBack : MedicalRequestEffect()
}