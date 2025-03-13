package com.wpay.authentication.domain.model

interface UserProfileStep {
    data object PersonalInfo : UserProfileStep
    data object AdditionalInfo : UserProfileStep
    data object Completed : UserProfileStep
    data object Canceled : UserProfileStep
}
