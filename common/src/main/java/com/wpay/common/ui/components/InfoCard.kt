package com.wpay.common.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wpay.common.ui.theme.primaryColor
import com.wpay.common.data.model.ButtonConfig
import com.wpay.common.data.model.ResultButtonConfigImp
import com.wpay.common.data.model.SimpleButtonConfigImp

@Composable
fun InfoCard(
    route: String? = null,
    fields: List<Pair<String, MutableState<String>>>,
    buttons: List<ButtonConfig>,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        fields.forEach { (label, state) ->
            OutlinedTextField(
                value = state.value,
                onValueChange = { state.value = it },
                label = { Text(label) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
        ) {
            buttons.forEach { button ->
                Button(
                    onClick = {
                        when (button) {
                            is SimpleButtonConfigImp -> {
                                button.onClick()
                            }

                            is ResultButtonConfigImp -> {
                                button.onClick(route)
                            }
                        }
                    },
                    shape = RoundedCornerShape(48.dp),
                    border = BorderStroke(1.dp, primaryColor),
                    colors = ButtonDefaults.buttonColors(containerColor = button.backgroundColor),
                    modifier = Modifier.width(140.dp)
                ) {
                    Text(button.text, color = button.textColor)
                }
            }
        }
    }
}


