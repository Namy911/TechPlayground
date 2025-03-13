package com.wpay.test_dagger.repository

import com.wpay.common.util.Result
import com.wpay.test_dagger.data.model.FakeApiService
import com.wpay.test_dagger.data.model.User
import com.wpay.common.data.repository.StatisticsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last

class UserRepository(
    private val apiService: FakeApiService,
    private val repository: StatisticsRepository,
) {
    fun fetchUsers(isConnected: Boolean) = flow {
        emit(Result.Loading)

        val dbUsers = repository.getAllUsers().firstOrNull()?.let { result ->
            if (result is Result.Success)
                result.data.map { User(it.id, it.name, it.email) } else emptyList()
        }
        delay(2000)
        if (isConnected && !dbUsers.isNullOrEmpty()) {
            apiService.addUser(dbUsers)
        }

        dbUsers?.let { emit(Result.Success(it)) }
            ?: Result.Error("Failed to retrieve users from database")
    }.catch { throwable ->
        val errorMessage = Result.Error(throwable.message ?: "Failed to load users")
        emit(errorMessage)
    }

    fun addUser(user: User, isConnected: Boolean) = flow {
        emit(Result.Loading)
        delay(2000)
        if (isConnected) {
            apiService.addUser(listOf(user))
        }
        repository.addUser(user.toUserEntity())

        emit(fetchUsers(isConnected).last())
    }.catch { throwable ->
        val errorMessage = Result.Error(throwable.message ?: "Failed to add user")
        emit(errorMessage)
    }

    fun fetchUserDetails(userId: Int, isConnected: Boolean) = flow {
        val userInfo = repository.getUser(userId).firstOrNull()?.let { result ->
            if (result is Result.Success) {
                val user = User(result.data.id, result.data.name, result.data.email)
                if (isConnected) {
                    apiService.addUser(listOf(user))
                }
                user
            } else {
                null
            }
        }

        userInfo?.let { emit(Result.Success(it)) } ?: emit(Result.Error("User not found"))
    }.catch { throwable ->
        val errorMessage = Result.Error(throwable.message ?: "User not found")
        emit(errorMessage)
    }

    fun fetchSettings() = flow {
        val settings = apiService.fetchSettings()

        if (settings.isEmpty()) {
            emit(Result.Error("No settings found"))
            return@flow
        }

        emit(Result.Success(settings))
    }.catch { throwable ->
        val errorMessage = Result.Error(throwable.message ?: "Failed to fetch settings")
        emit(errorMessage)
    }
}