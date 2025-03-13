package com.wpay.authentication.presentation.ui.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wpay.common.navigation.ScreenRoutes
import com.wpay.common.ui.theme.btnBackgroundColor
import com.wpay.common.ui.theme.primaryColor
import com.wpay.common.ui.extensions.getStyledText
import com.wpay.profile.R

@Composable
fun RegisterScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ScreenHeader(title = "Înregistrare")
        RegistrationForm(navController)
    }
}

@Composable
fun ScreenHeader(title: String) {
    Image(
        painter = painterResource(id = R.drawable.logo_2),
        contentDescription = "Your Image",
        modifier = Modifier.size(150.dp),
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

@Composable
fun RegistrationForm(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StyledTextInput(
            value = name,
            onValueChange = { name = it },
            label = "Nume, Prenume"
        )

        StyledTextInput(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            keyboardType = KeyboardType.Email
        )

        StyledTextInput(
            value = password,
            onValueChange = { password = it },
            label = "Parola",
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )

        StyledTextInput(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirma Parola",
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )

        CheckboxWithClickableLabel()
        val promptText = getStyledText("Ai un cont?", "Loghiază-te")
        BottomTextSection(
            btnText = "Add",
            promptText = promptText,
            onTextClick = { navController.navigate(route = ScreenRoutes.LoginScreen.route) },
            onBtnClick = {
                navController.navigate(route = ScreenRoutes.AccountScreen.route)
            }
        )
    }
}

@Composable
fun StyledTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = label,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = TextFieldDefaults.colors(
            focusedTextColor = primaryColor,
            unfocusedTextColor = Color.Gray,
            focusedLabelColor = primaryColor,
            unfocusedLabelColor = Color.Gray,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedIndicatorColor = primaryColor,
            unfocusedIndicatorColor = Color.Gray,
            cursorColor = primaryColor
        )
    )
}

@Composable
fun CheckboxWithClickableLabel() {
    var checked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            colors = CheckboxDefaults.colors(checkedColor = primaryColor),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = getStyledText("Acceptați", "termenii și condițiile"),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
        )
    }
}

@Composable
fun BottomTextSection(
    btnText: String,
    isButtonEnabled: Boolean = true,
    promptText: AnnotatedString,
    icon: Int? = null,
    onTextClick: () -> Unit,
    onBtnClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp),
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
                onTextClick.invoke()
            }
        )
    }
}

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
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = btnText,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(text = btnText, color = Color.White)
        }
    }
}
