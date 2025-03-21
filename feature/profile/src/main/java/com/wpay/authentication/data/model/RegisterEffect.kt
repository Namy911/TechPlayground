package com.wpay.authentication.data.model

import com.wpay.core.data.database.entity.User

sealed class RegisterEffect {
    data object NavigateToLogin : RegisterEffect()
    data class NavigateToAppointmentScreen(val user: User) : RegisterEffect()
    data class ToggleDialog(val isVisible: Boolean) : RegisterEffect()
    data class ShowSnackbar(val message: String) : RegisterEffect()
}