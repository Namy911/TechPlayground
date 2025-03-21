package com.wpay.core.data.repository

import com.wpay.core.data.database.dao.PrescriptionDao
import com.wpay.core.data.database.entity.Prescription
import com.wpay.core.domain.repository.PrescriptionRepository
import javax.inject.Inject

class PrescriptionRepositoryImp @Inject constructor(private val prescriptionDao: PrescriptionDao) :
    PrescriptionRepository {

    override suspend fun addPrescription(prescription: Prescription) {
        prescriptionDao.insertPrescription(prescription)
    }
}