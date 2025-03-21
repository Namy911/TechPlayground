package com.wpay.core.domain.repository

import com.wpay.core.data.database.entity.Prescription

interface PrescriptionRepository {
    suspend fun addPrescription(prescription: Prescription)
}