package com.wpay.authentication.data.validation

import com.wpay.core.domain.validation.ValidationResult
import com.wpay.core.domain.validation.Validator
import javax.inject.Inject

class EmailValidator @Inject constructor() : Validator<String> {
    override fun isValid(input: String?): ValidationResult {
        val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

        if (input.isNullOrBlank()) {
            return ValidationResult.Error("Email cannot be empty")
        }

        return if (emailPattern.matches(input)) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("Invalid email format")
        }
    }
}
