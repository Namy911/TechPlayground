package com.wpay.authentication.data.model

sealed class RegisterEffect {
    data object NavigateToLogin : RegisterEffect()
    data object NavigateToAppointmentScreen : RegisterEffect()
    data class ToggleDialog(val isVisible: Boolean) : RegisterEffect()
    data class ShowSnackbar(val message: String) : RegisterEffect()
}