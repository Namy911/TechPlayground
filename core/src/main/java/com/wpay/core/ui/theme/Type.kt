package com.wpay.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.wpay.core.R

val robotoFontFamily = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 50.sp,
        lineHeight = 64.sp,
        fontWeight = FontWeight.Bold
    ),
    displayMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        fontWeight = FontWeight.Normal
    ),
    displaySmall = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.Bold
    ),
    headlineMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Medium
    ),
    headlineSmall = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Medium
    ),
    titleLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium
    ),
    titleSmall = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    labelLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium
    ),
    labelMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    labelSmall = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium
    )
)
