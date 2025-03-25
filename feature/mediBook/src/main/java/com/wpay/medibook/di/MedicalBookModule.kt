package com.wpay.medibook.di

import com.wpay.core.domain.validation.Validator
import com.wpay.medibook.data.validation.LocationValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MedicalBookModule {
    @LocationValidatorQualifier
    @Provides
    fun provideLocationValidator(): Validator<String> {
        return LocationValidator()
    }
}