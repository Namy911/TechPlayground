package com.wpay.authentication.presentation.ui.screens.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wpay.core.ui.theme.primaryColor

@Composable
fun TermsAndConditionsDialog(isChecked: Boolean, onAccept: (Boolean) -> Unit, onClose: () -> Unit) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Terms and Conditions") },
        text = {
            Column {
                Text(
                    text = "By using this application, you agree to our Terms and Conditions...",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        colors = CheckboxDefaults.colors(checkedColor = primaryColor),
                        onCheckedChange = { onAccept.invoke(it) }
                    )
                    Text(text = "I agree to the Terms and Conditions")
                }
            }
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(primaryColor),
                onClick = { onClose() },
            ) {
                Text(
                    text = "Close",
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
    )
}
