package com.wpay.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wpay.core.data.database.dao.AppointmentDao
import com.wpay.core.data.database.dao.PrescriptionDao
import com.wpay.core.data.database.dao.SessionDurationDao
import com.wpay.core.data.database.dao.UserDao
import com.wpay.core.data.database.entity.Appointment
import com.wpay.core.data.database.entity.Prescription
import com.wpay.core.data.database.entity.SessionDuration
import com.wpay.core.data.database.entity.User

@Database(
    entities = [
        User::class, SessionDuration::class, Appointment::class, Prescription::class
    ],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun sessionDurationDao(): SessionDurationDao
    abstract fun appointmentDao(): AppointmentDao
    abstract fun prescriptionDao(): PrescriptionDao

}