package com.wpay.medibook.data.model

sealed class AppointmentEvent {
    data class OnRequestActionClick(val action: String) : AppointmentEvent()
}


enum class RequestAppointment(val value: String) {
    REQUEST_PRESCRIPTION("1"),
    SCHEDULE_CONSULTATION("2"),
    UNKNOWN_REQUEST("0"),
}