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
    val userChose = remember { mutableStateOf("") }

    var expandedCard by remember { mutableStateOf<String?>(requestId) }

    if (showDialog.value) {
        val dialogConfig = getDialogConfigForCardState(requestId)

        ConfirmationDialog(dialogConfig){
            showDialog.value = false
            navController.navigate(ScreenRoutes.ConsultationNav.route) {
                launchSingleTop = true
                popUpTo(ScreenRoutes.BookAppointmentScreen.route) { inclusive = true }
            }
        }
    }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                MedicalRequestEffect.CloseDatePicker -> TODO()
                MedicalRequestEffect.NavigateBack -> TODO()
                MedicalRequestEffect.ShowDatePicker -> TODO()
                MedicalRequestEffect.ShowRequestSuccess -> TODO()
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
            text = stringResource(string.book_consultation_title),
            imageId = R.drawable.calendar_24,
            activeFormImageId = if (uiState.consultExpandForm) drawable.navigate_next_2 else R.drawable.navigate_next,
            onClick = {
                viewModel.onEvent(
                    MedicalRequestEvent.ConsultationFormClicked
                )
            },
            activeFormSlot = {
                if (uiState.consultExpandForm) {
                    InfoCard(
                        route = "consutation",
                        fields = listOf(
                            "Date" to dateConsultation,
                            "Specialist" to specialistConsultation
                        ),
                        buttons = listOf(
                            SimpleButtonConfigImp(
                                text = stringResource(string.btn_txt_cancel),
                                textColor = primaryColor,
                                backgroundColor = Color.White
                            ) {
                                navController.popBackStack()
                            },
                            ResultButtonConfigImp(
                                text = stringResource(string.btn_txt_send),
                                backgroundColor = btnBackgroundColor,
                                textColor = Color.White
                            ) { route ->
                                userChose.value = route ?: ""
                                showDialog.value = true
                            }
                        )
                    )
                }
            },
        )
        BookConsultationCard(
            text = stringResource(string.book_consultation_message),
            imageId = drawable.book_24,
            activeFormImageId = if (uiState.prescriptExpandForm) drawable.navigate_next_2 else R.drawable.navigate_next,
            onClick = {
                viewModel.onEvent(
                    MedicalRequestEvent.PrescriptionFormClicked
                )
            },
            activeFormSlot = {
                if (uiState.prescriptExpandForm) {
                    InfoCard(
                        route = "prescription",
                        fields = listOf(
                            "Date" to datePrescription,
                            "Specialist" to specialistPrescription,
                            "Medical Center" to medicalCenter
                        ),
                        buttons = listOf(
                            SimpleButtonConfigImp(
                                text = stringResource(string.btn_txt_cancel),
                                backgroundColor = Color.White,
                                textColor = btnBackgroundColor
                            ) {
                                navController.popBackStack()
                            },
                            ResultButtonConfigImp(
                                text = stringResource(string.btn_txt_send),
                                backgroundColor = btnBackgroundColor,
                                textColor = Color.White
                            ) { route ->
                                userChose.value = route ?: ""
                                showDialog.value = true
                            }
                        )
                    )
                }
            }
        )
    }
}

fun getDialogConfigForCardState(expandedCard: String?): DialogConfig {
    return when (expandedCard) {
        SCHEDULE_CONSULTATION.value -> DialogConfig(
            imageResId = drawable.group_58,
            messageResId = string.consultation_success_message
        )

        REQUEST_PRESCRIPTION.value-> DialogConfig(
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

