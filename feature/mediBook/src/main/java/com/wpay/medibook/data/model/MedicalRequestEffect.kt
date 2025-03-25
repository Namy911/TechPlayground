package com.wpay.medibook.data.model

sealed class MedicalRequestEffect {
    data object ShowRequestSuccess : MedicalRequestEffect()
    data object ShowDatePicker : MedicalRequestEffect()
    data object CloseDatePicker : MedicalRequestEffect()
    data object NavigateBack : MedicalRequestEffect()
}