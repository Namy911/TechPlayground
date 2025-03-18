package com.wpay.authentication.di

import com.wpay.authentication.data.repository.AuthRepository
import com.wpay.authentication.data.validation.EmailValidatorImpl
import com.wpay.authentication.data.validation.PasswordValidatorImpl
import com.wpay.authentication.domain.repository.AuthRepositoryImp
import com.wpay.core.domain.UserRepository
import com.wpay.core.domain.validation.Validator
import com.wpay.core.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {
    @EmailValidator
    @Provides
    fun provideEmailValidator(): Validator<String> {
        return EmailValidatorImpl()
    }

    @PasswordValidator
    @Provides
    fun providePassValidator(): Validator<String> {
        return PasswordValidatorImpl()
    }

    @Provides
    fun provideAuthRepository(
        userRepository: UserRepository,
        dispatchers: DispatcherProvider,
    ): AuthRepository {
        return AuthRepositoryImp(dispatchers = dispatchers, userRepo = userRepository)
    }
}