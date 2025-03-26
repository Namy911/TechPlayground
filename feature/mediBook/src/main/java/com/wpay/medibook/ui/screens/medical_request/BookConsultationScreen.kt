package com.wpay.medibook.ui.screens.medical_request

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wpay.core.R
import com.wpay.core.data.model.ResultButtonConfigImp
import com.wpay.core.data.model.SimpleButtonConfigImp
import com.wpay.core.navigation.ScreenRoutes
import com.wpay.core.ui.components.InfoCard
import com.wpay.core.ui.theme.btnBackgroundColor
import com.wpay.core.ui.theme.primaryColor
import com.wpay.medibook.R.*
import com.wpay.medibook.data.model.DialogConfig
import com.wpay.medibook.data.model.MedicalRequestEffect
import com.wpay.medibook.data.model.MedicalRequestEvent
import com.wpay.medibook.data.model.RequestAppointment.REQUEST_PRESCRIPTION
import com.wpay.medibook.data.model.RequestAppointment.SCHEDULE_CONSULTATION
import com.wpay.medibook.viewmodel.MedicalRequestViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BookAppointmentScreen(
    requestId: String,
    navController: NavHostController,
    viewModel: MedicalRequestViewModel = hiltViewModel(),
) {
    val dateConsultation = remember { mutableStateOf("") }
    val specialistConsultation = remember { mutableStateOf("") }

    val datePrescription = remember { mutableStateOf("") }
    val specialistPrescription = remember { mutableStateOf("") }
    val medicalCenter = remember { mutableStateOf("") }

    val showDialog = remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()

    if (showDialog.value) {
        val dialogConfig = getDialogConfigForCardState(requestId)

        ConfirmationDialog(dialogConfig) {
            showDialog.value = false
            navController.navigate(ScreenRoutes.ConsultationNav.route) {
                launchSingleTop = true
                popUpTo(ScreenRoutes.BookAppointmentScreen.route) { inclusive = true }
            }
        }
    }


    LaunchedEffect(Unit) {
        viewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                is MedicalRequestEffect.CloseDatePicker -> TODO()
                is MedicalRequestEffect.NavigateBack -> TODO()
                is MedicalRequestEffect.ShowDatePicker -> TODO()
                is MedicalRequestEffect.ShowRequestSuccess -> {
                    if (effect.requestId == SCHEDULE_CONSULTATION.value) {
                        showDialog.value = true
                    }
                }
            }
        }
    }

    LaunchedEffect(requestId) {
        viewModel.onRequestSelected(requestId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        BookConsultationCard(
            uiState = uiState,
            activeFormImageId = getActiveImageId(uiState.consultExpandForm),
            viewModel = viewModel,
        )
        BookPrescriptionCard(
            uiState = uiState,
            activeFormImageId = getActiveImageId(uiState.prescriptExpandForm),
            viewModel = viewModel,
        )
    }
}

fun getActiveImageId(isExpanded: Boolean): Int {
    return if (isExpanded) {
        drawable.navigate_next_2
    } else {
        R.drawable.navigate_next
    }
}

fun getDialogConfigForCardState(expandedCard: String?): DialogConfig {
    return when (expandedCard) {
        SCHEDULE_CONSULTATION.value -> DialogConfig(
            imageResId = drawable.group_58,
            messageResId = string.consultation_success_message
        )

        REQUEST_PRESCRIPTION.value -> DialogConfig(
            imageResId = drawable.group_57,
            messageResId = string.prescription_success_message
        )

        else -> {
            DialogConfig(
                imageResId = drawable.group_57,
                messageResId = string.unknown_action_message
            )
        }
    }
}

