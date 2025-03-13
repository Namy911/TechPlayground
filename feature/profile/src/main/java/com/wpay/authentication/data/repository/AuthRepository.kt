package com.wpay.authentication.data.repository

import com.wpay.common.data.database.entity.User
import com.wpay.common.util.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String): Flow<Result<User?>>
}