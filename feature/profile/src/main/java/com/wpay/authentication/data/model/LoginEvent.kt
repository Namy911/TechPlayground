package com.wpay.authentication.data.model

sealed class LoginEvent {
    data class EmailChanged(val newEmail: String) : LoginEvent()
    data class PasswordChanged(val newPassword: String) : LoginEvent()
    data class RememberPasswordToggled(val isChecked: Boolean) : LoginEvent()
    data class LoginRequested(val onSuccess: () -> Unit) : LoginEvent()
    data object ClearLoginError : LoginEvent()
}
