package com.wpay.authentication.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wpay.core.ui.theme.primaryColor
import com.wpay.profile.R

@Composable
fun ScreenHeader(title: String) {
    Image(
        painter = painterResource(id = R.drawable.logo_2),
        contentDescription = "Your Image",
        modifier = Modifier.size(140.dp),
        contentScale = ContentScale.Fit
    )

    Spacer(modifier = Modifier.height(32.dp))

    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.displayLarge,
        color = primaryColor,
        textAlign = TextAlign.Center
    )
}