package com.wpay.test_dagger.repository

import android.adservices.adid.AdId
import com.wpay.test_dagger.data.model.FakeApiService
import com.wpay.test_dagger.data.model.User
import kotlinx.coroutines.delay

class UserRepository(private val apiService: FakeApiService) {
    suspend fun fetchUsers(): List<User> = apiService.fetchUsers()

    suspend fun fetchUserDetails(userId: Int, userName: String, userEmail: String): User =
        apiService.fetchUserDetails(userId, userName, userEmail)

    suspend fun fetchSettings(): String  = apiService.fetchSettings()
}