package com.wpay.authentication.data.validation

import com.wpay.core.domain.validation.ValidationResult
import com.wpay.core.domain.validation.Validator
import javax.inject.Inject

class TermsValidator @Inject constructor(): Validator<Boolean> {
    override fun isValid(input: Boolean?): ValidationResult {
        return if (input == true) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("You must accept the terms and conditions to proceed.")
        }
    }
}
