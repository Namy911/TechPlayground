package com.wpay.authentication.data

import androidx.compose.ui.graphics.Color

data class ButtonConfig(
    val text: String,
    val backgroundColor: Color,
    val textColor: Color,
    val onClick: () -> Unit
)
