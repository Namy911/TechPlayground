package com.wpay.core.domain.repository

import com.wpay.core.data.database.entity.User
import com.wpay.core.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {
    val userId: StateFlow<Long?>
    fun setUserId(id: Long)
    fun getUserProfile(login: String, password: String): Flow<User?>
    fun getUserProfileById(id: Long): Flow<Result<User>>
    suspend fun insertUser(fullName: String, email: String, password: String): Result<User>
}