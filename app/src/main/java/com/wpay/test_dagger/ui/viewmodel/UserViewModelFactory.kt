package com.wpay.test_dagger.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wpay.common.util.DispatcherProvider
import com.wpay.test_dagger.repository.UserRepository
import com.wpay.test_dagger.util.NetworkManager

class UserViewModelFactory(
    private val repository: UserRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val networkManager: NetworkManager,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository, dispatcherProvider, networkManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
