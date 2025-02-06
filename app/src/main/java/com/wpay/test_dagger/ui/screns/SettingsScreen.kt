package com.wpay.test_dagger.ui.screns

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wpay.test_dagger.ui.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController, viewModel: UserViewModel) {
    LaunchedEffect(Unit) {
        viewModel.fetchSettings()
    }

    val settingsState by viewModel.settings.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Settings") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            when (settingsState) {
                is UserViewModel.Result.Loading -> CircularProgressIndicator()
                is UserViewModel.Result.Success -> Text(
                    (settingsState as UserViewModel.Result.Success<String>).data,
                    style = MaterialTheme.typography.bodyMedium
                )
                is UserViewModel.Result.Error -> Text("Error loading settings")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("user_list") }) {
                Text("Back to Users")
            }
        }
    }
}
