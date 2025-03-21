package com.wpay.core.data.repository

import com.wpay.core.data.database.dao.AppointmentDao
import com.wpay.core.data.database.entity.Appointment
import com.wpay.core.domain.repository.ConsultationRepository
import javax.inject.Inject

class ConsultationRepositoryImp @Inject constructor(private val appointmentDao: AppointmentDao) :
    ConsultationRepository {

    override suspend fun addConsultation(appointment: Appointment) {
        appointmentDao.insertAppointment(appointment)
    }
}
