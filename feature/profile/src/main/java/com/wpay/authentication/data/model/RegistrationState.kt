package com.wpay.authentication.data.model

data class RegistrationState(
    val name: String = "Test, Test",
    val email: String = "ffff@ff.ggg",
    val password: String = "123456Q!q",
    val confirmPass: String = "123456Q!q",
    val isTermsAccepted: Boolean = true,
    val hasTermsError: Boolean = false,

    val emailError: String? = null,
    val passwordError: String? = null,
    val passConfirmError: String? = null,
    val nameError: String? = null,

    val isLoading: Boolean = false,
)