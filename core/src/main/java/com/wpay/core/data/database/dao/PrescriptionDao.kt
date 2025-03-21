package com.wpay.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wpay.core.data.database.entity.Prescription

@Dao
interface PrescriptionDao {

    @Insert
    suspend fun insertPrescription(prescription: Prescription)

    @Query("SELECT * FROM prescriptions WHERE userId = :userId")
    suspend fun getPrescriptionsForUser(userId: Long): List<Prescription>
}