package com.wpay.test_dagger.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.wpay.test_dagger.data.model.User
import com.wpay.test_dagger.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableStateFlow<Result>(Result.Loading)
    val users: StateFlow<Result> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.value = Result.Loading
            try {
                val data = repository.getUsers()
                _users.value = Result.Success(data)
            } catch (e: Exception) {
                _users.value = Result.Error(e.message ?: "Unknown Error")
            }
        }
    }

    sealed class Result {
        object Loading : Result()
        data class Success(val users: List<User>) : Result()
        data class Error(val message: String) : Result()
    }
}