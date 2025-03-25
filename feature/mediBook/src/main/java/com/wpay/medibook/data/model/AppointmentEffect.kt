package com.wpay.medibook.data.model

sealed class AppointmentEffect {
    data object RequestPrescription : AppointmentEffect()
    data object ScheduleConsultation : AppointmentEffect()
}