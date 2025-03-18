package com.wpay.authentication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wpay.authentication.data.model.RegisterEffect
import com.wpay.authentication.data.model.RegisterEvent
import com.wpay.authentication.data.model.RegistrationState
import com.wpay.authentication.di.EmailValidatorQualifier
import com.wpay.authentication.di.FullNameValidatorQualifier
import com.wpay.authentication.di.MatchingPassValidatorQualifier
import com.wpay.authentication.di.PasswordValidatorQualifier
import com.wpay.authentication.di.TermsValidatorQualifier
import com.wpay.core.data.database.entity.User
import com.wpay.core.domain.UserRepository
import com.wpay.core.domain.validation.Validator
import com.wpay.core.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    @EmailValidatorQualifier private val emailValidator: Validator<String>,
    @FullNameValidatorQualifier private val fullNameValidator: Validator<String>,
    @PasswordValidatorQualifier private val passwordValidator: Validator<String>,
    @MatchingPassValidatorQualifier private val matchingPassValidator: Validator<Pair<String, String>>,
    @TermsValidatorQualifier private val termsAcceptedValidator: Validator<Boolean>,
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationState())
    val uiState: StateFlow<RegistrationState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<RegisterEffect>(replay = 0, extraBufferCapacity = 1)
    val uiEffect: SharedFlow<RegisterEffect> = _uiEffect.asSharedFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        viewModelScope.launch {
//            _effect.emit(RegisterEffect.ShowError("Failed to save user: ${throwable.localizedMessage}"))
        }
    }

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailChanged -> updateEmail(event.email)
            is RegisterEvent.ConfirmPasswordChanged -> updateConfirmPassword(event.confirmPassword)
            is RegisterEvent.NameChanged -> updateName(event.name)
            is RegisterEvent.PasswordChanged -> updatePassword(event.password)
            is RegisterEvent.TermsAcceptedChanged -> setTermsAccepted(event.isAccepted)
            is RegisterEvent.Submit -> validateForm()
            is RegisterEvent.LoginTextClicked -> onLoginTextClicked()
            is RegisterEvent.ToggleDialog -> triggerDialog(event.isVisible)
        }
    }

    private fun updateEmail(email: String) {
        _uiState.update { currentState ->
            currentState.copy(
                email = email,
                emailError = currentState.emailError?.let { null }
            )
        }
    }

    private fun updatePassword(password: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = password,
                passwordError = currentState.passwordError?.let { null }
            )
        }
    }

    private fun updateConfirmPassword(confirmPassword: String) {
        _uiState.update { currentState ->
            currentState.copy(
                confirmPass = confirmPassword,
                passConfirmError = currentState.passConfirmError?.let { null }
            )
        }
    }

    private fun updateName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = name,
                nameError = currentState.nameError?.let { null }
            )
        }
    }

    private fun setTermsAccepted(isTermsAccepted: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isTermsAccepted = isTermsAccepted,
                hasTermsError = false
            )
        }
    }

    private fun onLoginTextClicked() {
        viewModelScope.launch {
            _uiEffect.emit(RegisterEffect.NavigateToLogin)
        }
    }

    private fun triggerDialog(isVisible: Boolean) {
        viewModelScope.launch {
            _uiEffect.emit(RegisterEffect.ToggleDialog(isVisible))
        }
    }

    private fun validateForm() {
        viewModelScope.launch(exceptionHandler) {
            _uiState.update { it.copy(isLoading = true) }
            val currentState = _uiState.value

            if (isRegistrationFormValid(currentState)) {
                insertUser(currentState)
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun isRegistrationFormValid(currentState: RegistrationState): Boolean {
        val emailError = emailValidator.isValid(currentState.email).getErrorMessage()
        val nameError = fullNameValidator.isValid(currentState.name).getErrorMessage()
        val passwordError = passwordValidator.isValid(currentState.password).getErrorMessage()
        val confirmPassError = matchingPassValidator
            .isValid(Pair(currentState.password, currentState.confirmPass))
            .getErrorMessage()
        val termsError = termsAcceptedValidator
            .isValid(currentState.isTermsAccepted)
            .getErrorMessage()

        _uiState.update {
            it.copy(
                emailError = emailError,
                nameError = nameError,
                passwordError = passwordError,
                passConfirmError = confirmPassError,
                hasTermsError = termsError != null
            )
        }
        viewModelScope.launch {
            termsError?.let { message -> _uiEffect.emit(RegisterEffect.ShowSnackbar(message)) }
        }

        val list = listOf(emailError, nameError, passwordError, confirmPassError, termsError)
        return list.all { it.isNullOrBlank() }
    }

    private suspend fun insertUser(currentState: RegistrationState) {
        val newUser = User(
            email = currentState.email,
            name = currentState.name,
            password = currentState.password
        )
        when (userRepository.insertUser(newUser)) {
            is Result.Success -> {
                _uiEffect.emit(RegisterEffect.NavigateToAppointmentScreen)
            }

            is Result.Error -> {
//                _effect.emit(RegisterEffect.ShowError(throwable.localizedMessage))
            }

            is Result.Loading -> {}
        }
    }
}
