package com.wpay.authentication.data.model

sealed class RegisterEvent {
    data class EmailChanged(val email: String) : RegisterEvent()
    data class PasswordChanged(val password: String) : RegisterEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterEvent()
    data class NameChanged(val name: String) : RegisterEvent()
    data class TermsAcceptedChanged(val isAccepted: Boolean) : RegisterEvent()
    data class ToggleDialog(val isVisible: Boolean) : RegisterEvent()
    data object LoginTextClicked : RegisterEvent()
    data object Submit : RegisterEvent()
}