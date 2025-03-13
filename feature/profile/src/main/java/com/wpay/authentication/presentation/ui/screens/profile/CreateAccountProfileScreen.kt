package com.wpay.authentication.presentation.ui.screens.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wpay.authentication.data.model.UserProfileData
import com.wpay.authentication.domain.model.UserProfileStep
import com.wpay.authentication.domain.model.UserProfileStep.AdditionalInfo
import com.wpay.authentication.domain.model.UserProfileStep.Canceled
import com.wpay.authentication.domain.model.UserProfileStep.Completed
import com.wpay.authentication.domain.model.UserProfileStep.PersonalInfo

@Composable
fun CreateAccountProfileScreen(
    onNavigateBack: () -> Unit,
    handleExit: () -> Unit,
) {
    BackHandler { onNavigateBack() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        var currentStep by remember { mutableStateOf<UserProfileStep>(PersonalInfo) }
        var userProfileData by remember { mutableStateOf(UserProfileData()) }

        when (currentStep) {
            is PersonalInfo -> {
                DetailedCardWithProgress()
                PersonalInfoScreen(
                    userProfileData = userProfileData,
                    onUpdate = { updatedData -> userProfileData = updatedData },
                    onNext = { currentStep = AdditionalInfo },
                    onCancel = { currentStep = Canceled }
                )
            }

            is AdditionalInfo -> {
                DetailedCardWithProgress()
                AdditionalInfoScreen(
                    userProfileData = userProfileData,
                    onUpdate = { updatedData -> userProfileData = updatedData },
                    onBack = { currentStep = PersonalInfo },
                    onFinish = { currentStep = Completed }
                )
            }

            is Completed -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                )

                LaunchedEffect(Unit) { onNavigateBack() }
            }

            else -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                )
                LaunchedEffect(Unit) {
                    handleExit()
                }
            }
        }
    }
}
