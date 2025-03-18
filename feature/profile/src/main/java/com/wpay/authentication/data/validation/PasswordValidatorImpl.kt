package com.wpay.authentication.data.validation

import com.wpay.core.domain.validation.ValidationResult
import com.wpay.core.domain.validation.Validator
import javax.inject.Inject

class PasswordValidatorImpl @Inject constructor() : Validator<String> {
    override fun isValid(input: String?): ValidationResult {
        if (input.isNullOrBlank()) {
            return ValidationResult.Error("Password cannot be empty")
        }
        if (input.length < 8) {
            return ValidationResult.Error("Password must be at least 8 characters")
        }
        if (!input.any { it.isDigit() }) {
            return ValidationResult.Error("Password must contain at least one number")
        }
        return ValidationResult.Success
    }
}