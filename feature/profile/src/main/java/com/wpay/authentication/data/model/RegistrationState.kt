package com.wpay.authentication.data.model

data class RegistrationState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPass: String = "",
    val isTermsAccepted: Boolean = false,
    val hasTermsError: Boolean = false,

    val emailError: String? = null,
    val passwordError: String? = null,
    val passConfirmError: String? = null,
    val nameError: String? = null,

    val isLoading: Boolean = false,
)