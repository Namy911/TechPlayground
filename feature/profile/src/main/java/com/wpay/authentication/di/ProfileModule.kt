package com.wpay.authentication.di

import com.wpay.authentication.data.repository.AuthRepository
import com.wpay.authentication.data.validation.EmailValidator
import com.wpay.authentication.data.validation.FullNameValidator
import com.wpay.authentication.data.validation.MatchingPassValidator
import com.wpay.authentication.data.validation.PasswordValidator
import com.wpay.authentication.data.validation.TermsValidator
import com.wpay.authentication.domain.repository.AuthRepositoryImp
import com.wpay.core.domain.UserRepository
import com.wpay.core.domain.validation.Validator
import com.wpay.core.util.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {
    @Provides
    fun provideAuthRepository(
        userRepository: UserRepository,
        dispatchers: DispatcherProvider,
    ): AuthRepository {
        return AuthRepositoryImp(dispatchers = dispatchers, userRepo = userRepository)
    }

    @MatchingPassValidatorQualifier
    @Provides
    fun provideMatchingPassValidator(): Validator<Pair<String, String>> {
        return MatchingPassValidator()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {
        @FullNameValidatorQualifier
        @Binds
        fun provideFullNameValidator(validator: FullNameValidator): Validator<String>

        @TermsValidatorQualifier
        @Binds
        fun bindTermsValidator(validator: TermsValidator): Validator<Boolean>

        @EmailValidatorQualifier
        @Binds
        fun bindEmailValidator(validator: EmailValidator): Validator<String>

        @PasswordValidatorQualifier
        @Binds
        fun bindPassValidator(validator: PasswordValidator): Validator<String>
    }
}