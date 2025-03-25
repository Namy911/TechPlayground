package com.wpay.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DataStoreFileName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatabaseName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FullNameValidatorQualifier