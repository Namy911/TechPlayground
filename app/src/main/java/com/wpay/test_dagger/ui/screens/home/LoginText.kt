package com.wpay.test_dagger.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wpay.common.navigation.ScreenRoutes
import com.wpay.common.ui.extensions.getStyledText
import com.wpay.profile.R

@Composable
fun LoginText(navController: NavHostController) {
    Text(
        text = getStyledText(
            mainText = stringResource(R.string.account_prompt_text),
            subText = stringResource(R.string.login_text)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable {
                navController.navigate(route = ScreenRoutes.LoginScreen.route)
            },
        style = MaterialTheme.typography.bodyLarge,
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}