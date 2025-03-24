package com.wpay.test_dagger.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wpay.core.util.DispatcherProvider
import com.wpay.core.util.Result
import com.wpay.test_dagger.data.model.User
import com.wpay.test_dagger.data.repository.UserRepository2
import com.wpay.test_dagger.util.NetworkManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository2,
    private val dispatcher: DispatcherProvider,
    networkManager: NetworkManager,
) : ViewModel() {

    private val _users = MutableStateFlow<Result<List<User>>>(Result.Loading)
    val users: StateFlow<Result<List<User>>> = _users

    private val _userDetails = MutableStateFlow<Result<User>>(Result.Loading)
    val userDetails: StateFlow<Result<User>> = _userDetails

    private val _settings = MutableStateFlow<Result<String>>(Result.Loading)
    val settings: StateFlow<Result<String>> = _settings

    private val isConnected = networkManager
        .isConnected
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false
        )

    init {
        viewModelScope.launch(dispatcher.io) {
            isConnected.collectLatest {
                fetchUsers()
            }
        }
    }

    private suspend fun fetchUsers() {
        repository.fetchUsers(isConnected.value).collectLatest {
            _users.value = it
        }
    }

    fun fetchUserDetails(userId: Int) {
        viewModelScope.launch(dispatcher.io) {
            repository.fetchUserDetails(userId, isConnected.value).collectLatest {
                _userDetails.value = it
            }
        }
    }

    fun fetchSettings() {
        viewModelScope.launch(dispatcher.io) {
            repository.fetchSettings().collectLatest {
                _settings.value = it
            }
        }
    }

    fun addUserOnFabClick(user: User) {
        viewModelScope.launch(dispatcher.io) {
            repository.addUser(user, isConnected.value).collectLatest {
                _users.value = it
            }
        }
    }
}
