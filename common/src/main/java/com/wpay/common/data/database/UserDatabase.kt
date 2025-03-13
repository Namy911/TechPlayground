package com.wpay.common.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wpay.common.data.database.dao.SessionDurationDao
import com.wpay.common.data.database.dao.UserDao
import com.wpay.common.data.database.entity.SessionDuration
import com.wpay.common.data.database.entity.User

@Database(entities = [User::class, SessionDuration::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun sessionDurationDao(): SessionDurationDao

}