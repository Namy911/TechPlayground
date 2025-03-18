package com.wpay.authentication.presentation.ui.screens.registration

import androidx.compose.foundation.clickable
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
import com.wpay.core.ui.extensions.getStyledText
import com.wpay.core.ui.theme.primaryColor
import com.wpay.profile.R

@Composable
fun CheckboxWithClickableLabel(
    isTermsAccepted: Boolean,
    isError: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onTermsClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Checkbox(
            checked = isTermsAccepted,
            onCheckedChange = { onCheckedChange(it) },
            colors = CheckboxDefaults.colors(
                checkedColor = primaryColor,
                uncheckedColor = if (isError) Color.Red else Color.Gray,
                checkmarkColor = Color.White
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = getStyledText(
                mainText = stringResource(R.string.terms_txt_first),
                subText = stringResource(R.string.terms_txt_second),
                isError = isError
            ),
            style = MaterialTheme.typography.bodyLarge,
            color = if (isError) Color.Red else Color.Black,
            modifier = Modifier.clickable {
                onTermsClick.invoke()
            }
        )
    }
}