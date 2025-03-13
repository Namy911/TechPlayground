package com.wpay.common.data.model

import androidx.compose.ui.graphics.Color

interface ButtonConfig {
    val text: String
    val backgroundColor: Color
    val textColor: Color
}

interface SimpleButtonConfig : ButtonConfig {
    val onClick: () -> Unit
}

interface ResultButtonConfig : ButtonConfig {
    val onClick: (String) -> Unit
}

data class SimpleButtonConfigImp(
    override val text: String,
    override val backgroundColor: Color,
    override val textColor: Color,
    override val onClick: () -> Unit,
) : ButtonConfig, SimpleButtonConfig

data class ResultButtonConfigImp(
    override val text: String,
    override val backgroundColor: Color,
    override val textColor: Color,
    override val onClick: (String?) -> Unit,
) : ButtonConfig, ResultButtonConfig