package com.wpay.medibook.ui.screens.medical_request

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wpay.core.R.drawable
import com.wpay.core.ui.components.CustomTextField
import com.wpay.core.ui.components.DateTimePicker
import com.wpay.core.ui.theme.btnBackgroundColor
import com.wpay.core.ui.theme.primaryColor
import com.wpay.medibook.R
import com.wpay.medibook.data.model.MedicalRequestEvent
import com.wpay.medibook.data.model.MedicalRequestState
import com.wpay.medibook.viewmodel.MedicalRequestViewModel

@Composable
fun BookPrescriptionCard(
    uiState: MedicalRequestState,
    viewModel: MedicalRequestViewModel,
    @DrawableRes activeFormImageId: Int = drawable.navigate_next,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp)
            .then(Modifier.animateContentSize())
            .clickable {
                viewModel.onEvent(
                    MedicalRequestEvent.ConsultationFormClicked
                )
            },
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Image(
                painter = painterResource(R.drawable.book_24),
                contentDescription = null,
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp)
            )
            Text(
                text = stringResource(R.string.book_prescription_title),
                color = btnBackgroundColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(activeFormImageId),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .padding(top = 8.dp)
            )

        }
        if (uiState.prescriptExpandForm) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.height(0.dp))
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    DateTimePicker()

                    CustomTextField(
                        value = uiState.prescriptSpecialist,
                        onValueChange = {
//                    viewModel.onEvent()
                        },
                        label = "",
                        isError = false,
                        errorMessage = "",
                        placeholder = "Specialist",
                    )

                    CustomTextField(
                        value = uiState.prescriptDate,
                        onValueChange = {
//                    viewModel.onEvent()
                        },
                        label = "",
                        isError = false,
                        errorMessage = "",
                        placeholder = "Specialist",
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp,
                            Alignment.CenterHorizontally
                        )
                    ) {
                        Button(
                            onClick = {
                            },
                            shape = RoundedCornerShape(48.dp),
                            border = BorderStroke(1.dp, primaryColor),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            modifier = Modifier.width(140.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.btn_txt_cancel),
                                color = btnBackgroundColor
                            )
                        }
                        Button(
                            onClick = {
                            },
                            shape = RoundedCornerShape(48.dp),
                            border = BorderStroke(1.dp, primaryColor),
                            colors = ButtonDefaults.buttonColors(containerColor = btnBackgroundColor),
                            modifier = Modifier.width(140.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.btn_txt_send),
                                color = Color.White
                            )
                        }
                    }

                }
            }
        }
    }
}

