package com.wpay.authentication.presentation.ui.screens.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wpay.core.ui.theme.btnBackgroundColor
import com.wpay.authentication.data.model.UserProfileData
import com.wpay.core.data.model.SimpleButtonConfigImp
import com.wpay.core.ui.components.InfoCard

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

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        InfoCard(
            fields = listOf(
                "Name" to name,
                "Surname" to surname,
                "Phone" to phone
            ),
            buttons = listOf(
                SimpleButtonConfigImp("Cancel", Color.White, btnBackgroundColor, onCancel),
                SimpleButtonConfigImp("Next", btnBackgroundColor, Color.White, onNext)
            )
        )
    }
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
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        InfoCard(
            fields = listOf(
                "Address" to address,
                "ID" to id,
                "Location" to location
            ),
            buttons = listOf(
                SimpleButtonConfigImp("Back", Color.White, btnBackgroundColor, onBack),
                SimpleButtonConfigImp("Finish", btnBackgroundColor, Color.White, onFinish)
            )
        )
    }
}
