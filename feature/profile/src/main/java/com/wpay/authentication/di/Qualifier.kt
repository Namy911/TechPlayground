package com.wpay.authentication.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EmailValidatorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PasswordValidatorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TermsValidatorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MatchingPassValidatorQualifier