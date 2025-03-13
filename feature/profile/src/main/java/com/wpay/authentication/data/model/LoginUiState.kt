package com.wpay.authentication.data.model

data class LoginUiState (
    val login: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isRemembered: Boolean = false,
    val loginError: String? = null
)
