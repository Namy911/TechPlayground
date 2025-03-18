package com.wpay.core.data.datastore

import androidx.datastore.core.DataStore
import com.wpay.core.domain.model.UserPreferences
import com.wpay.core.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepositoryImp @Inject constructor(
    private val dataStore: DataStore<UserPreferences>,
) : UserPreferencesRepository {
    override suspend fun updatePassword(password: String) {
        dataStore.updateData { preferences ->
            preferences.toBuilder()
                .setDarkMode(password)
                .build()
        }
    }

    override suspend fun updateLogin(login: String) {
        dataStore.updateData { preferences ->
            preferences.toBuilder()
                .setLogin(login)
                .build()
        }
    }

    override suspend fun updateCheckedState(isChecked: Boolean) {
        dataStore.updateData { preferences ->
            preferences.toBuilder()
                .setCheckedState(isChecked)
                .build()
        }
    }

    override suspend fun getLogin(): String = withContext(Dispatchers.IO) {
        val preferences = dataStore.data.first()
        preferences.login
    }

    override suspend fun getPassword(): String = withContext(Dispatchers.IO) {
        val preferences = dataStore.data.first()
        preferences.password
    }

    override suspend fun getCheckedState(): Boolean = withContext(Dispatchers.IO) {
        val preferences = dataStore.data.first()
        preferences.isRemembered
    }

    override fun getUserPreferences(): Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is Exception) {
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }
}
