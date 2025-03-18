package com.wpay.core.domain.repository

import com.wpay.core.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    suspend fun updatePassword(password: String)
    suspend fun updateLogin(login: String)
    suspend fun updateCheckedState(isChecked: Boolean)
    suspend fun getPassword(): String
    suspend fun getLogin(): String
    suspend fun getCheckedState(): Boolean
    fun getUserPreferences(): Flow<UserPreferences>
}