package com.wpay.medibook.ui.screens.medical_request

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wpay.core.ui.theme.primaryColor
import com.wpay.medibook.data.model.DialogConfig

@Composable
fun ConfirmationDialog(dialogConfig: DialogConfig, onButtonClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = { },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                dialogConfig.imageResId?.let { resId ->
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = "Dialog Image",
                        modifier = Modifier
                            .height(58.dp)
                            .width(48.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                dialogConfig.messageResId?.let { resId ->
                    Text(
                        text = stringResource(resId),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = onButtonClick,
                        colors = ButtonDefaults.buttonColors(primaryColor),
                        modifier = Modifier.width(128.dp)
                    ) {
                        Text("Inchideti", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        },
        confirmButton = { }
    )
}