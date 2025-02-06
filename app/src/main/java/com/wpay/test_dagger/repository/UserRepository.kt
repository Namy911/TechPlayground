package com.wpay.test_dagger.repository

import com.wpay.test_dagger.data.model.FakeApiService
import com.wpay.test_dagger.data.model.User

class UserRepository(private val apiService: FakeApiService) {
    suspend fun getUsers(): List<User> = apiService.getUsers()
}