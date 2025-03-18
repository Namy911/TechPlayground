package com.wpay.authentication.data.validation

import com.wpay.core.domain.validation.ValidationResult
import com.wpay.core.domain.validation.Validator
import javax.inject.Inject

class MatchingPassValidator @Inject constructor() : Validator<Pair<String, String>> {
    override fun isValid(input: Pair<String, String>?): ValidationResult {
        val (password, confirmPassword) = input ?: return ValidationResult.Error("Password and confirmation cannot be empty")

        return when {
            password != confirmPassword -> {
                ValidationResult.Error("Passwords do not match")
            }
            password.isBlank() || confirmPassword.isBlank() -> {
                ValidationResult.Error("Password and confirmation cannot be empty")
            }
            else -> {
                ValidationResult.Success
            }
        }
    }
}
