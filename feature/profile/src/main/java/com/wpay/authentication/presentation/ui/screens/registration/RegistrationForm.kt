package com.wpay.authentication.presentation.ui.screens.registration

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.wpay.authentication.data.model.RegisterEvent
import com.wpay.authentication.data.model.RegistrationState
import com.wpay.authentication.presentation.viewmodel.RegistrationViewModel
import com.wpay.core.ui.components.CustomTextField
import com.wpay.core.ui.extensions.getStyledText
import com.wpay.core.ui.theme.primaryColor
import com.wpay.profile.R

@Composable
fun RegistrationForm(
    uiState: RegistrationState,
    viewModel: RegistrationViewModel,
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(80.dp),
                color = primaryColor,
            )
        }
        Column {
            CustomTextField(
                value = uiState.name,
                onValueChange = { viewModel.onEvent(RegisterEvent.NameChanged(it)) },
                label = stringResource(R.string.labe_name),
                isError = uiState.nameError != null,
                errorMessage = uiState.nameError,
                isFormDisabled = !uiState.isLoading,
                keyboardType = KeyboardType.Text
            )

            CustomTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEvent(RegisterEvent.EmailChanged(it)) },
                label = stringResource(R.string.labe_email),
                isError = uiState.emailError != null,
                errorMessage = uiState.emailError,
                isFormDisabled = !uiState.isLoading,
                keyboardType = KeyboardType.Email
            )

            CustomTextField(
                value = uiState.password,
                onValueChange = { viewModel.onEvent(RegisterEvent.PasswordChanged(it)) },
                label = stringResource(R.string.label_pssword),
                isError = uiState.passwordError != null,
                errorMessage = uiState.passwordError,
                isFormDisabled = !uiState.isLoading,
                placeholder = stringResource(R.string.placeholder_pass),
                visualTransformation = PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password
            )

            CustomTextField(
                value = uiState.confirmPass,
                onValueChange = { viewModel.onEvent(RegisterEvent.ConfirmPasswordChanged(it)) },
                label = stringResource(R.string.label_pssword_confim),
                isError = uiState.passConfirmError != null,
                errorMessage = uiState.passConfirmError,
                isFormDisabled = !uiState.isLoading,
                placeholder = stringResource(R.string.placeholder_pass),
                visualTransformation = PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password
            )

            CheckboxWithClickableLabel(
                isTermsAccepted = uiState.isTermsAccepted,
                isError = uiState.hasTermsError,
                onCheckedChange = { isAccepted ->
                    viewModel.onEvent(
                        RegisterEvent.TermsAcceptedChanged(isAccepted)
                    )
                },
                onTermsClick = {
                    viewModel.onEvent(RegisterEvent.ToggleDialog(true))
                }
            )

            val promptText = getStyledText(
                mainText = stringResource(R.string.register_prompt_first),
                subText = stringResource(R.string.login_txt)
            )
            BottomTextSection(
                btnText = stringResource(R.string.btn_text_register),
                icon = R.drawable.group_12,
                promptText = promptText,
                onLoginTextClick = { viewModel.onEvent(RegisterEvent.LoginTextClicked) },
                onBtnClick = { viewModel.onEvent(RegisterEvent.Submit) }
            )
        }
    }
}