package com.wpay.authentication.data.validation

import com.wpay.common.domain.validation.ValidationResult
import com.wpay.common.domain.validation.Validator
import javax.inject.Inject

class EmailValidatorImpl @Inject constructor() : Validator<String> {
    private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

    override fun isValid(input: String?): ValidationResult {
        if (input?.matches(EMAIL_REGEX) == false) {
            return ValidationResult.Error("Invalid email format")
        }
        return ValidationResult.Success
    }
}