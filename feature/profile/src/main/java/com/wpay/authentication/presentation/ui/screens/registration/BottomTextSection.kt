package com.wpay.authentication.presentation.ui.screens.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BottomTextSection(
    btnText: String,
    isButtonEnabled: Boolean = true,
    promptText: AnnotatedString,
    icon: Int? = null,
    onLoginTextClick: () -> Unit,
    onBtnClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegisterButtonWithIcon(
            onClick = onBtnClick,
            btnText = btnText,
            isEnabled = isButtonEnabled,
            icon = icon
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = promptText,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable {
                onLoginTextClick.invoke()
            }
        )
    }
}

