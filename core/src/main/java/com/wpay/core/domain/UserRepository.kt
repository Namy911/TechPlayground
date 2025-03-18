package com.wpay.core.domain

import com.wpay.core.data.database.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserProfile(login: String, password: String): Flow<User?>
}