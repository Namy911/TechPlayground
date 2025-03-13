package com.wpay.test_dagger.ui.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wpay.common.navigation.ScreenRoutes
import com.wpay.common.ui.theme.btnBackgroundColor
import com.wpay.profile.R

@Composable
fun RegisterButton(navController: NavHostController) {
    Button(
        onClick = {
            navController.navigate(ScreenRoutes.RegisterScreen.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(btnBackgroundColor),
    ) {
        Text(text = stringResource(R.string.create_new_account_btn_text), color = Color.White)
    }
}

