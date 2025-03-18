package com.wpay.authentication.presentation.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wpay.authentication.data.model.LoginUiState
import com.wpay.authentication.presentation.ui.screens.ScreenHeader
import com.wpay.authentication.presentation.ui.screens.registration.BottomTextSection
import com.wpay.authentication.presentation.viewmodel.LoginViewModel
import com.wpay.core.navigation.ScreenRoutes
import com.wpay.core.ui.components.CustomTextField
import com.wpay.core.ui.extensions.getStyledText
import com.wpay.core.ui.theme.primaryColor
import com.wpay.profile.R

@Composable
fun LoginForm(
    isButtonEnabled: Boolean,
    isLoading: Boolean = false,
    navController: NavHostController,
    uiState: LoginUiState,
    viewModel: LoginViewModel,
) {
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.Center),
                color = primaryColor,
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ScreenHeader(stringResource(R.string.login_header))

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = uiState.login,
            onValueChange = { viewModel.onEmailChange(it) },
            label = stringResource(R.string.labe_email),
            isError = uiState.loginError != null,
            isFormDisabled = !uiState.isLoading,
            keyboardType = KeyboardType.Email
        )

        CustomTextField(
            value = uiState.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = stringResource(R.string.label_pssword),
            isError = uiState.loginError != null,
            isFormDisabled = !uiState.isLoading,
            placeholder = stringResource(R.string.placeholder_pass),
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        Box(modifier = Modifier.offset(y = (-24).dp)) {
            LabeledCheckbox(
                isRemembered = uiState.isRemembered,
                onRememberChange = { viewModel.onRememberPassToggle(it) },
            )
        }

        val promptText = getStyledText(
            mainText = stringResource(R.string.prompt_acc_part1),
            subText = stringResource(R.string.prompt_acc_part2)
        )
        BottomTextSection(
            btnText = stringResource(R.string.login_txt),
            isButtonEnabled = isButtonEnabled && !isLoading,
            promptText = promptText,
            onLoginTextClick = {
                navController.navigate(ScreenRoutes.RegisterScreen.route)
            },
            onBtnClick = {
                viewModel.onLogin {
                    navController.navigate(ScreenRoutes.ConsultationNav.route)
                }
            },
        )
    }
}
