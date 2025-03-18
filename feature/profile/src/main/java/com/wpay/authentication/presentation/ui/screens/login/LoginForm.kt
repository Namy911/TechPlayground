package com.wpay.authentication.presentation.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.wpay.authentication.presentation.ui.screens.registration.BottomTextSection
import com.wpay.authentication.presentation.ui.screens.registration.ScreenHeader
import com.wpay.core.ui.components.CustomTextField
import com.wpay.core.ui.extensions.getStyledText
import com.wpay.core.ui.theme.primaryColor
import com.wpay.profile.R

@Composable
fun LoginForm(
    email: String,
    isButtonEnabled: Boolean,
    isRemembered: Boolean,
    password: String,
    isError: Boolean = false,
    isLoading: Boolean = false,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onBtnClick: () -> Unit,
    onTextClick: () -> Unit,
    onRememberChange: (Boolean) -> Unit,
) {
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.Center),
                color = primaryColor,
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ScreenHeader(title = stringResource(R.string.login_header))

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = email,
            onValueChange = { onEmailChange(it) },
            label = stringResource(R.string.labe_email),
            isError = isError,
            isFormDisabled = !isLoading,
            keyboardType = KeyboardType.Email
        )

        CustomTextField(
            value = password,
            onValueChange = { onPasswordChange(it) },
            label = stringResource(R.string.label_pssword),
            isError = isError,
            isFormDisabled = !isLoading,
            placeholder = stringResource(R.string.placeholder_pass),
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        Box(modifier = Modifier.offset(y = (-16).dp)) {
            LabeledCheckbox(
                isRemembered = isRemembered,
                onRememberChange = onRememberChange,
            )
        }

        val promptText = getStyledText(
            mainText = stringResource(R.string.prompt_acc_part1),
            subText = stringResource(R.string.prompt_acc_part2)
        )
        BottomTextSection(
            btnText = stringResource(R.string.btn_login_txt),
            isButtonEnabled = isButtonEnabled && !isLoading,
            promptText = promptText,
            onTextClick = { onTextClick.invoke() },
            onBtnClick = { onBtnClick.invoke() },
        )
    }
}
