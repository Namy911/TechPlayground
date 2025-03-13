package com.wpay.common.domain.validation

interface Validator<T> {
    fun isValid(input: T?): ValidationResult
}