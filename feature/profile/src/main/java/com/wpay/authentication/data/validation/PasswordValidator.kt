package com.wpay.authentication.data.validation

import com.wpay.core.domain.validation.ValidationResult
import com.wpay.core.domain.validation.Validator
import javax.inject.Inject

class PasswordValidator @Inject constructor() : Validator<String> {
    override fun isValid(input: String?): ValidationResult {
        val pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}$".toRegex()

        if (input.isNullOrBlank()) {
            return ValidationResult.Error("Password cannot be empty")
        }

        return if (pattern.matches(input)) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("Password must be at least 8 characters, include uppercase, lowercase, number, and special character")
        }
    }
}
