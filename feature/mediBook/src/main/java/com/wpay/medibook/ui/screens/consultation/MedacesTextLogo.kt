package com.wpay.medibook.ui.screens.consultation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wpay.common.ui.theme.primaryColor

@Composable
fun MedacesTextLogo() {
    val prefixText = "Bun venit la "
    val startPos = prefixText.length
    val medacesText = buildAnnotatedString {
        append("Bun venit la ")
        append("MED")
        addStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            start = startPos,
            end = startPos + 3
        )

        append("ACES")
        addStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = primaryColor
            ),
            start = startPos + 3,
            end = startPos + 7
        )
    }

    Text(
        text = medacesText,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}