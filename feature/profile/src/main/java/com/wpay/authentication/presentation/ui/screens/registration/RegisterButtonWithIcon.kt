package com.wpay.authentication.presentation.ui.screens.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.wpay.core.ui.theme.btnBackgroundColor

@Composable
fun RegisterButtonWithIcon(
    onClick: () -> Unit,
    isEnabled: Boolean,
    btnText: String,
    icon: Int? = null,
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(btnBackgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 24.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            if (icon != null) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = btnText,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            Text(text = btnText, color = Color.White, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
