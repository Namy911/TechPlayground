package com.wpay.medibook.ui.screens.consultation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wpay.core.R.drawable
import com.wpay.core.navigation.ScreenRoutes
import com.wpay.core.ui.components.MedicalCardItem
import com.wpay.core.ui.components.sampleUsers
import com.wpay.medibook.R
import com.wpay.medibook.data.model.AppointmentEffect
import com.wpay.medibook.data.model.AppointmentEvent
import com.wpay.medibook.data.model.RequestAppointment.REQUEST_PRESCRIPTION
import com.wpay.medibook.data.model.RequestAppointment.SCHEDULE_CONSULTATION
import com.wpay.medibook.viewmodel.AppointmentViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppointmentScreen(
    navigation: NavHostController,
    viewModel: AppointmentViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                is AppointmentEffect.RequestPrescription -> navigation.navigate(
                    "${ScreenRoutes.BookAppointmentScreen.route}/${REQUEST_PRESCRIPTION.value}"
                )

                is AppointmentEffect.ScheduleConsultation -> navigation.navigate(
                    "${ScreenRoutes.BookAppointmentScreen.route}/${SCHEDULE_CONSULTATION.value}"
                )
            }
        }
    }
//    if (uiState.isLoading) {
//        Box(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .size(80.dp)
//                    .align(Alignment.Center),
//                color = primaryColor,
//            )
//        }
//    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Salut ${uiState.userName}", style = MaterialTheme.typography.headlineSmall)

            MedacesTextLogo()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Cu ce va pot ajuta ?",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 24.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AppointmentCard(
                        text = "Programeazate o \nconsultatie",
                        imageId = drawable.calendar_24,
                        modifier = Modifier
                            .weight(1f)
                            .height(148.dp)
                            .width(148.dp)
                            .clickable {
                                viewModel.onEvent(AppointmentEvent.OnRequestActionClick(
                                    SCHEDULE_CONSULTATION.value
                                ))
                            }
                    )
                    AppointmentCard(
                        text = "Solicita o\nreceta",
                        imageId = R.drawable.book_24,
                        modifier = Modifier
                            .weight(1f)
                            .height(148.dp)
                            .width(148.dp)
                            .clickable {
                                viewModel.onEvent(AppointmentEvent.OnRequestActionClick(
                                    REQUEST_PRESCRIPTION.value
                                ))
                            }
                    )
                }

                Text(
                    text = "Programari curente",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 24.dp)
                )

                LazyColumn {
                    items(sampleUsers) { user ->
                        MedicalCardItem(user, Modifier)
                    }
                }
            }
        }
    }

//}