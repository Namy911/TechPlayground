package com.wpay.test_dagger.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wpay.core.navigation.ScreenRoutes
import com.wpay.core.util.Result
import com.wpay.test_dagger.ui.viewmodel.UserViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: UserViewModel,
    onNavigateBack: () -> Unit,
) {
    val context = LocalContext.current
    val settingsState by viewModel.settings.collectAsState()

    BackHandler { onNavigateBack() }

    LaunchedEffect(Unit) {
        viewModel.fetchSettings()
        startSessionTracking(context, "1", ScreenRoutes.SettingsScreen.route)
    }

    DisposableEffect(Unit) {
        onDispose {
            stopSessionTracking(context)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (settingsState) {
            is Result.Loading -> CircularProgressIndicator()
            is Result.Success -> Text(
                (settingsState as Result.Success<String>).data,
                style = MaterialTheme.typography.bodyMedium
            )

            is Result.Error -> Text("Error loading settings")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(ScreenRoutes.UserListScreenScreen.route)
        }) {
            Text("Back to Users")
        }
    }
}
