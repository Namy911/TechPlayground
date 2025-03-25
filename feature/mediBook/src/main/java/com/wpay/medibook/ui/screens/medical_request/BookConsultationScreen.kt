package com.wpay.medibook.ui.screens.medical_request

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.wpay.medibook.data.model.RequestAppointment.REQUEST_PRESCRIPTION
import com.wpay.medibook.data.model.RequestAppointment.SCHEDULE_CONSULTATION
import com.wpay.medibook.viewmodel.MedicalRequestViewModel

@Composable
fun BookAppointmentScreen(
    actionId: String,
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

    var expandedCard by remember { mutableStateOf<String?>(actionId) }

    if (showDialog.value) {
        val dialogConfig = getDialogConfigForCardState(expandedCard)

        ConfirmationDialog(dialogConfig){
            showDialog.value = false
            navController.navigate(ScreenRoutes.ConsultationNav.route) {
                launchSingleTop = true
                popUpTo(ScreenRoutes.BookAppointmentScreen.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        BookConsultationCard(
            text = "Programeazate o \nconsultatie",
            imageId = R.drawable.calendar_24,
            activeFormImageId = if (expandedCard == SCHEDULE_CONSULTATION.value) drawable.navigate_next_2 else R.drawable.navigate_next,
            onClick = {
                expandedCard =
                    if (expandedCard == SCHEDULE_CONSULTATION.value) null else SCHEDULE_CONSULTATION.value
            },
            activeFormSlot = {
                if (expandedCard == SCHEDULE_CONSULTATION.value) {
                    InfoCard(
                        route = "consutation",
                        fields = listOf(
                            "Date" to dateConsultation,
                            "Specialist" to specialistConsultation
                        ),
                        buttons = listOf(
                            SimpleButtonConfigImp(
                                text = "Cancel",
                                textColor = primaryColor,
                                backgroundColor = Color.White
                            ) {
                                navController.popBackStack()
                            },
                            ResultButtonConfigImp(
                                text = "Send",
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
            text = "Solicita o\nreceta",
            imageId = drawable.book_24,
            activeFormImageId = if (expandedCard == REQUEST_PRESCRIPTION.value) drawable.navigate_next_2 else R.drawable.navigate_next,
            onClick = {
                expandedCard =
                    if (expandedCard == REQUEST_PRESCRIPTION.value) null else REQUEST_PRESCRIPTION.value
            },
            activeFormSlot = {
                if (expandedCard == REQUEST_PRESCRIPTION.value) {
                    InfoCard(
                        route = "prescription",
                        fields = listOf(
                            "Date" to datePrescription,
                            "Specialist" to specialistPrescription,
                            "Medical Center" to medicalCenter
                        ),
                        buttons = listOf(
                            SimpleButtonConfigImp(
                                text = "Cancel",
                                backgroundColor = Color.White,
                                textColor = btnBackgroundColor
                            ) {
                                navController.popBackStack()
                            },
                            ResultButtonConfigImp(
                                text = "Send",
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

