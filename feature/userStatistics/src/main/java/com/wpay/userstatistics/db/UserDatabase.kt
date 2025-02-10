package com.wpay.userstatistics.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wpay.userstatistics.model.SessionDurationDao
import com.wpay.userstatistics.model.UserDao
import com.wpay.userstatistics.model.entity.SessionDuration
import com.wpay.userstatistics.model.entity.User

@Database(entities = [User::class, SessionDuration::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun sessionDurationDao(): SessionDurationDao

    companion object {
        @Volatile private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java, "user_db"
                ).build().also { instance = it }
            }
    }
}