package com.wpay.core.domain.validation

interface Validator<T> {
    fun isValid(input: T?): ValidationResult
}