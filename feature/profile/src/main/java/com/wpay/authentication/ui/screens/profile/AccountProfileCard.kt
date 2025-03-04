package com.wpay.authentication.ui.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.medacces.core.ui.theme.btnBackgroundColor
import com.medacces.core.ui.theme.primaryTextColor
import com.wpay.authentication.data.ButtonConfig
import com.wpay.authentication.data.UserProfileData

@Composable
fun PersonalInfoScreen(
    userProfileData: UserProfileData,
    onUpdate: (UserProfileData) -> Unit,
    onNext: () -> Unit,
    onCancel: () -> Unit,
) {
    val name = remember { mutableStateOf(userProfileData.name) }
    val surname = remember { mutableStateOf(userProfileData.surname) }
    val phone = remember { mutableStateOf(userProfileData.phone) }

    InfoCard(
        fields = listOf(
            "Name" to name,
            "Surname" to surname,
            "Phone" to phone
        ),
        buttons = listOf(
            ButtonConfig("Cancel", Color.White, btnBackgroundColor, onCancel),
            ButtonConfig("Next", btnBackgroundColor, Color.White, onNext)
        )
    )
}

@Composable
fun AdditionalInfoScreen(
    userProfileData: UserProfileData,
    onUpdate: (UserProfileData) -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit,
) {
    val address = remember { mutableStateOf(userProfileData.address) }
    val id = remember { mutableStateOf(userProfileData.id) }
    val location = remember { mutableStateOf(userProfileData.location) }

    InfoCard(
        fields = listOf(
            "Address" to address,
            "ID" to id,
            "Location" to location
        ),
        buttons = listOf(
            ButtonConfig("Back", Color.White, btnBackgroundColor, onBack),
            ButtonConfig("Finish", btnBackgroundColor, Color.White, onFinish)
        )
    )
}

@Composable
fun InfoCard(
    fields: List<Pair<String, MutableState<String>>>,
    buttons: List<ButtonConfig>
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            fields.forEach { (label, state) ->
                OutlinedTextField(
                    value = state.value,
                    onValueChange = { state.value = it },
                    label = { Text(label) },
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                buttons.forEach { button ->
                    Button(
                        onClick = button.onClick,
                        shape = RoundedCornerShape(48.dp),
                        border = BorderStroke(1.dp, primaryTextColor),
                        colors = ButtonDefaults.buttonColors(containerColor = button.backgroundColor),
                        modifier = Modifier.width(140.dp)
                    ) {
                        Text(button.text, color = button.textColor)
                    }
                }
            }
        }
    }
}


