package com.wpay.authentication.data.validation

import com.wpay.core.domain.validation.ValidationResult
import com.wpay.core.domain.validation.Validator
import javax.inject.Inject

class FullNameValidator @Inject constructor() : Validator<String> {
    override fun isValid(input: String?): ValidationResult {
        if (input.isNullOrBlank()) {
            return ValidationResult.Error("Field cannot be empty")
        }
        val parts = input.split(",").map { it.trim() }

        if (parts.size != 2) {
            return ValidationResult.Error("Use format: Name,Surname")
        }

        val (name, surname) = parts

        if (name.isEmpty() || surname.isEmpty()) {
            return ValidationResult.Error("Both name and surname are required")
        }

        val namePattern = "^[A-Za-z]+(?:'[A-Za-z]+)*$".toRegex()

        if (!name.matches(namePattern) || !surname.matches(namePattern)) {
            return ValidationResult.Error("Only letters and single apostrophes are allowed")
        }

        return ValidationResult.Success
    }
}
