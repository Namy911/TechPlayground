package com.wpay.test_dagger.data.model

import kotlinx.coroutines.delay

class FakeApiService {
    suspend fun getUsers(): List<User> {
        delay(2000) // Simulate network delay
        return listOf(
            User(1, "John Doe", "john@example.com"),
            User(2, "Jane Doe", "jane@example.com"),
            User(3, "Alice Smith", "alice@example.com")
        )
    }
}