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
import com.medacces.core.ui.theme.btnBackgroundColor
import com.medacces.core.ui.theme.primaryTextColor
import com.wpay.common.R
import com.wpay.common.data.ResultButtonConfigImp
import com.wpay.common.data.SimpleButtonConfigImp
import com.wpay.common.navigation.ScreenRoutes
import com.wpay.common.ui.InfoCard
import com.wpay.medibook.R.drawable

@Composable
fun BookAppointmentScreen(activeFormId: Int, navController: NavHostController) {
    val date = remember { mutableStateOf("") }
    val specialist = remember { mutableStateOf("") }

    val date_2 = remember { mutableStateOf("") }
    val specialist_2 = remember { mutableStateOf("") }
    val medicalCenter = remember { mutableStateOf("") }

    val showDialog = remember { mutableStateOf(false) }
    val userChose = remember { mutableStateOf("") }

    var expandedCard by remember { mutableStateOf<Int?>(activeFormId) }

    if (showDialog.value) {
        val data = if (expandedCard == 1) {
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
            activeFormImageId = if (expandedCard == 1) drawable.navigate_next_2 else R.drawable.navigate_next,
            onClick = { expandedCard = if (expandedCard == 1) null else 1 },
            activeFormSlot = {
                if (expandedCard == 1) {
                    InfoCard(
                        route = "consutation",
                        fields = listOf(
                            "Date" to date,
                            "Specialist" to specialist
                        ),
                        buttons = listOf(
                            SimpleButtonConfigImp(
                                text = "Cancel",
                                textColor = primaryTextColor,
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
            activeFormImageId = if (expandedCard == 2) drawable.navigate_next_2 else R.drawable.navigate_next,
            onClick = { expandedCard = if (expandedCard == 2) null else 2 },
            activeFormSlot = {
                if (expandedCard == 2) {
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

