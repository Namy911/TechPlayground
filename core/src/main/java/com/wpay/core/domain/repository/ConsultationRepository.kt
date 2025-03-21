package com.wpay.core.domain.repository

import com.wpay.core.data.database.entity.Appointment

interface ConsultationRepository {
    suspend fun addConsultation(appointment: Appointment)
}