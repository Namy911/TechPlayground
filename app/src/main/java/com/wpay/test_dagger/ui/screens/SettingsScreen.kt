package com.wpay.test_dagger.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wpay.common.navigation.ScreenRoutes
import com.wpay.common.util.Result
import com.wpay.test_dagger.ui.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: UserViewModel
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchSettings()
        startSessionTracking(context, "1",ScreenRoutes.SettingsScreen.route)
    }

    DisposableEffect(Unit) {
        onDispose {
            stopSessionTracking(context)
        }
    }
    val settingsState by viewModel.settings.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text(ScreenRoutes.SettingsScreen.route) }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
}
