package com.wpay.test_dagger.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wpay.test_dagger.data.model.User
import com.wpay.test_dagger.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    sealed class Result<out T> {
        object Loading : Result<Nothing>()
        data class Success<T>(val data: T) : Result<T>()
        data class Error(val message: String) : Result<Nothing>()
    }

    private val _users = MutableStateFlow<Result<List<User>>>(Result.Loading)
    val users: StateFlow<Result<List<User>>> = _users

    private val _userDetails = MutableStateFlow<Result<User>>(Result.Loading)
    val userDetails: StateFlow<Result<User>> = _userDetails

    private val _settings = MutableStateFlow<Result<String>>(Result.Loading)
    val settings: StateFlow<Result<String>> = _settings

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.value = Result.Loading
            try {
                val usersList = repository.fetchUsers()
                _users.value = Result.Success(usersList)
            } catch (e: Exception) {
                _users.value = Result.Error("Failed to load users")
            }
        }
    }

    fun fetchUserDetails(userId: Int, userName: String, userEmail: String) {
        viewModelScope.launch {
            _userDetails.value = Result.Loading
            try {
                val user = repository.fetchUserDetails(userId ,userName, userEmail)
                _userDetails.value = Result.Success(user)
            } catch (e: Exception) {
                _userDetails.value = Result.Error("Failed to load user details")
            }
        }
    }

    fun fetchSettings() {
        viewModelScope.launch {
            _settings.value = Result.Loading
            try {
                val settingsData = repository.fetchSettings()
                _settings.value = Result.Success(settingsData)
            } catch (e: Exception) {
                _settings.value = Result.Error("Failed to load settings")
            }
        }
    }
}
