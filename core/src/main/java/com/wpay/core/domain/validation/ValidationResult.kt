package com.wpay.core.domain.validation

sealed class ValidationResult {
    data object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()

    fun getErrorMessage(): String? {
        return when (this) {
            is Success -> null
            is Error -> message
        }
    }
}