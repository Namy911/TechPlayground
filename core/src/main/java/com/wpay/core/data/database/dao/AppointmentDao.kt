package com.wpay.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wpay.core.data.database.entity.Appointment

@Dao
interface AppointmentDao {

    @Insert
    suspend fun insertAppointment(appointment: Appointment)

    @Query("SELECT * FROM appointments WHERE userId = :userId")
    suspend fun getAppointmentsForUser(userId: Long): List<Appointment>
}