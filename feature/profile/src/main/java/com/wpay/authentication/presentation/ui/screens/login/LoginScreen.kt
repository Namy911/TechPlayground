package com.wpay.authentication.presentation.ui.screens.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wpay.authentication.presentation.viewmodel.AuthViewModel
import com.wpay.core.navigation.ScreenRoutes

@Composable
fun LoginScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val isButtonEnabled = uiState.login.isNotBlank() && uiState.password.isNotBlank()

    LaunchedEffect(uiState.loginError) {
        uiState.loginError?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearLoginError()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues()),
    ) {

        LoginForm(
            email = uiState.login,
            isButtonEnabled = isButtonEnabled,
            isRemembered = uiState.isRemembered,
            password = uiState.password,
            isError = uiState.loginError != null,
            isLoading = uiState.isLoading,
            onEmailChange = { viewModel.onEmailChange(it) },
            onPasswordChange = { viewModel.onPasswordChange(it) },
            onBtnClick = {
                viewModel.onLogin {
                    navController.navigate(ScreenRoutes.ConsultationNav.route)
                }
            },
            onTextClick = {
                navController.navigate(ScreenRoutes.RegisterScreen.route)
            },
            onRememberChange = {
                viewModel.onRememberPassToggle(it)
            }
        )
    }
}