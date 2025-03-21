package com.wpay.medibook.data.model

sealed class AppointmentEvent {

    data class OnAppointmentBooked(val appointmentId: Long) : AppointmentEvent()

//    data class OnAppointmentCancelled(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentUpdated(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentSelected(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentDeselected(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentDateSelected(val date: String) : AppointmentEvent()
//
//    data class OnAppointmentTimeSelected(val time: String) : AppointmentEvent()
//
//    data class OnAppointmentSpecialistSelected(val specialist: String) : AppointmentEvent()
//
//    data class OnAppointmentUserSelected(val userId: Long) : AppointmentEvent()
//
//    data class OnAppointmentBookedConfirmed(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentBookedCancelled(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentBookedUpdated(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentBookedSelected(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentBookedDeselected(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentBookedDateSelected(val date: String) : AppointmentEvent()
//
//    data class OnAppointmentBookedTimeSelected(val time: String) : AppointmentEvent()
//
//    data class OnAppointmentBookedSpecialistSelected(val specialist: String) : AppointmentEvent()
//
//    data class OnAppointmentBookedUserSelected(val userId: Long) : AppointmentEvent()
//
//    data class OnAppointmentBookedConfirmedConfirmed(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentBookedConfirmedCancelled(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentBookedConfirmedUpdated(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentBookedConfirmedSelected(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentBookedConfirmedDeselected(val appointmentId: Long) : AppointmentEvent()
//
//    data class OnAppointmentBookedConfirmedDateSelected(val date: String) : AppointmentEvent()
//
//    data class OnAppointmentBookedConfirmedTimeSelected(val time: String) : AppointmentEvent()
//
//    data class OnAppointmentBookedConfirmedSpecialistSelected(val specialist: String) : AppointmentEvent()
//
//    data class OnAppointment
}