package com.wpay.medibook.data.validation

import com.wpay.core.domain.validation.ValidationResult
import com.wpay.core.domain.validation.Validator
import javax.inject.Inject

class LocationValidator @Inject constructor() : Validator<String> {
    override fun isValid(input: String?): ValidationResult {
        val regex = "^[^,\\.]+,[^,\\.]+\$".toRegex()

        return if (input?.matches(regex) == true) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("Invalid location format. Please use 'City, District'.")
        }
    }
}