package com.wpay.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wpay.core.data.database.dao.SessionDurationDao
import com.wpay.core.data.database.dao.UserDao
import com.wpay.core.data.database.entity.SessionDuration
import com.wpay.core.data.database.entity.User

@Database(entities = [User::class, SessionDuration::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun sessionDurationDao(): SessionDurationDao

}