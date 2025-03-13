package com.wpay.common.domain

import com.wpay.common.data.database.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserProfile(username: String, password: String): Flow<User?>
}