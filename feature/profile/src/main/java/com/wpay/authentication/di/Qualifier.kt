package com.wpay.authentication.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EmailValidator

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PasswordValidator