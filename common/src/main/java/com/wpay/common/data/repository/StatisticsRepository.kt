package com.wpay.common.data.repository

import com.wpay.common.util.Result
import com.wpay.common.data.database.UserDatabase
import com.wpay.common.data.database.entity.User
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StatisticsRepository @Inject constructor (private val db: UserDatabase) {

    suspend fun addUser(users: User) = db.userDao().insertUser(users)

    fun getUser(id: Int) = db.userDao().getUserById(id)
        .map { user ->
            user?.let {
                Result.Success(it)
            } ?: Result.Error("User not found")
        }
        .catch { throwable ->
            val errorMessage =
                Result.Error(throwable.message ?: "An error occurred while fetching user data")
            emit(errorMessage)
        }

    fun getAllUsers() = db.userDao().getAllUsers()
        .map { users ->
            if (users.isNotEmpty()) {
                Result.Success(users)
            } else {
                Result.Error("No users found")
            }
        }
        .catch { throwable ->
            val errorMessage = Result.Error(throwable.message ?: "An error occurred while fetching all users")
            emit(errorMessage)
        }

    suspend fun getUserSessions(userId: Int) = db.sessionDurationDao().getUserSessions(userId)
}