package com.wpay.authentication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wpay.authentication.data.model.LoginUiState
import com.wpay.authentication.data.repository.AuthRepository
import com.wpay.core.domain.repository.UserPreferencesRepository
import com.wpay.core.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPrefRepo: UserPreferencesRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    login = userPrefRepo.getLogin(),
                    password = userPrefRepo.getPassword(),
                    isRemembered = userPrefRepo.getCheckedState()
                )
            }
        }
    }

    fun onEmailChange(newLogin: String) {
        _uiState.update { currentState ->
            currentState.copy(
                login = newLogin,
                loginError = currentState.loginError?.let { null }
            )
        }
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = newPassword,
                loginError = currentState.loginError?.let { null }
            )
        }
    }

    fun onRememberPassToggle(isChecked: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isRemembered = isChecked)
        }
    }

    fun onLogin(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            if (uiState.value.isRemembered) {
                userPrefRepo.updateLogin(login = uiState.value.login.trim())
                userPrefRepo.updatePassword(password = uiState.value.password.trim())
                userPrefRepo.updateCheckedState(isChecked = uiState.value.isRemembered)
            }
            delay(2000)

            authRepository.login(
                username = _uiState.value.login.trim(),
                password = _uiState.value.password.trim()
            ).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update { it.copy(isLoading = false) }
                        onSuccess()
                    }

                    is Result.Error -> {
                        _uiState.update { it.copy(isLoading = false, loginError = result.message) }
                    }

                    Result.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    fun clearLoginError() {
        _uiState.update { currentState ->
            currentState.copy(
                loginError = currentState.loginError?.let { null }
            )
        }
    }
}