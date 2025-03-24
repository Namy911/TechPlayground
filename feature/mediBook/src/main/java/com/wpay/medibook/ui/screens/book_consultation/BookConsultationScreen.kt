package com.wpay.medibook.ui.screens.book_consultation

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
import androidx.navigation.NavHostController
import com.wpay.core.R
import com.wpay.core.data.model.ResultButtonConfigImp
import com.wpay.core.data.model.SimpleButtonConfigImp
import com.wpay.core.navigation.ScreenRoutes
import com.wpay.core.ui.components.InfoCard
import com.wpay.core.ui.theme.btnBackgroundColor
import com.wpay.core.ui.theme.primaryColor
import com.wpay.medibook.R.drawable
import com.wpay.medibook.data.model.RequestAppointment.REQUEST_PRESCRIPTION
import com.wpay.medibook.data.model.RequestAppointment.SCHEDULE_CONSULTATION

@Composable
fun BookAppointmentScreen(actionId: String, navController: NavHostController) {
    val date = remember { mutableStateOf("") }
    val specialist = remember { mutableStateOf("") }

    val date_2 = remember { mutableStateOf("") }
    val specialist_2 = remember { mutableStateOf("") }
    val medicalCenter = remember { mutableStateOf("") }

    val showDialog = remember { mutableStateOf(false) }
    val userChose = remember { mutableStateOf("") }

    var expandedCard by remember { mutableStateOf<String?>(actionId) }

    if (showDialog.value) {
        val data = if (expandedCard == SCHEDULE_CONSULTATION.value) {
            Pair(drawable.group_58, "Programarea a fost completată cu succes")
        } else {
            Pair(drawable.group_57, "Receta a fost trimisă cu succes")
        }
        ConfirmationDialog(
            data = data,
            onButtonClick = {
                showDialog.value = false
                navController.navigate(ScreenRoutes.ConsultationNav.route) {
                    launchSingleTop = true
                    popUpTo(ScreenRoutes.BookAppointmentScreen.route) { inclusive = true }
                }
            }
        )
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
            onClick = { expandedCard = if (expandedCard == SCHEDULE_CONSULTATION.value) null else SCHEDULE_CONSULTATION.value },
            activeFormSlot = {
                if (expandedCard == SCHEDULE_CONSULTATION.value) {
                    InfoCard(
                        route = "consutation",
                        fields = listOf(
                            "Date" to date,
                            "Specialist" to specialist
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
            onClick = { expandedCard = if (expandedCard == REQUEST_PRESCRIPTION.value) null else REQUEST_PRESCRIPTION.value },
            activeFormSlot = {
                if (expandedCard == REQUEST_PRESCRIPTION.value) {
                    InfoCard(
                        route = "prescription",
                        fields = listOf(
                            "Date" to date_2,
                            "Specialist" to specialist_2,
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

