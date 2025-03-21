package com.wpay.authentication.presentation.ui.screens.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wpay.authentication.data.model.RegisterEffect
import com.wpay.authentication.data.model.RegisterEvent
import com.wpay.authentication.presentation.ui.screens.ScreenHeader
import com.wpay.authentication.presentation.viewmodel.RegistrationViewModel
import com.wpay.core.navigation.ScreenRoutes
import com.wpay.profile.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    viewModel: RegistrationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                is RegisterEffect.NavigateToLogin -> navController.navigate(
                    ScreenRoutes.LoginScreen.route
                )

                is RegisterEffect.NavigateToAppointmentScreen -> navController.navigate(
                    "${ ScreenRoutes.AppointmentScreen.route }/${effect.user.id}"
                )

                is RegisterEffect.ShowSnackbar -> snackbarHostState.showSnackbar(effect.message)

                is RegisterEffect.ToggleDialog -> showDialog = effect.isVisible
            }
        }
    }

    if (showDialog) {
        TermsAndConditionsDialog(
            isChecked = uiState.isTermsAccepted,
            onAccept = { isAccepted ->
                viewModel.onEvent(
                    RegisterEvent.TermsAcceptedChanged(isAccepted)
                )
            },
            onClose = {
                viewModel.onEvent(
                    RegisterEvent.ToggleDialog(false)
                )
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ScreenHeader(stringResource(R.string.txt_registration_header))

        Spacer(modifier = Modifier.height(16.dp))

        RegistrationForm(uiState = uiState, viewModel = viewModel)
    }
}


