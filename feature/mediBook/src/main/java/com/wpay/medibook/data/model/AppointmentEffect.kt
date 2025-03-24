package com.wpay.medibook.data.model

import com.wpay.core.data.database.entity.User

sealed class AppointmentEffect {
    data object RequestPrescription : AppointmentEffect()
    data object ScheduleConsultation : AppointmentEffect()

}