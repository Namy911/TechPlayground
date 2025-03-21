package com.wpay.medibook.data.model

import com.wpay.core.data.database.entity.Appointment

data class AppointmentSate (
    val appointments: List<Appointment> = emptyList(),
    val userName: String = "Unknown",
    val isLoading: Boolean = false,
)