package com.wpay.common.ui.extensions

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.wpay.common.ui.theme.primaryColor

fun getStyledText(mainText: String, subText: String): AnnotatedString {
    val nonBreakingSpace = "\u00A0"
    return buildAnnotatedString {
        append(mainText)
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.SemiBold,
                textDecoration = TextDecoration.Underline,
                color = primaryColor
            )
        ) {
            append(nonBreakingSpace)
            append(subText)
        }
    }
}