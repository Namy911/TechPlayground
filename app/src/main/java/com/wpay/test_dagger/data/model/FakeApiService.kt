package com.wpay.test_dagger.data.model

import kotlinx.coroutines.delay

class FakeApiService {
    suspend fun fetchUsers(): List<User> {
        delay(2000) // Simulate network delay
        return listOf(
            User(1, "John Doe", "john@example.com"),
            User(2, "Jane Doe", "jane@example.com"),
            User(3, "Alice Smith", "alice@example.com")
        )
    }

    suspend fun fetchUserDetails(userId: Int, userName: String, userEmail: String): User {
        delay(1500)
        return User(userId, userName, userEmail)
    }

    suspend fun fetchSettings(): String {
        delay(2500)
        return "Dark Mode: ON\nNotifications: Enabled"
    }
}