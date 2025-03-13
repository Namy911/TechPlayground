package com.wpay.authentication.presentation.ui.screens.login

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wpay.common.ui.theme.primaryColor
import com.wpay.profile.R

@Composable
fun LabeledCheckbox(
    isRemembered : Boolean,
    onRememberChange: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Checkbox(
            checked = isRemembered,
            onCheckedChange = { onRememberChange(it) },
            colors = CheckboxDefaults.colors(checkedColor = primaryColor),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(R.string.remember_pass_txt),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
        )
    }
}