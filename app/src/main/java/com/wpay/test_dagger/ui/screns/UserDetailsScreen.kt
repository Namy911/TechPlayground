package com.wpay.test_dagger.ui.screns

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wpay.test_dagger.data.model.User
import com.wpay.test_dagger.ui.viewmodel.UserViewModel
import com.wpay.test_dagger.ui.viewmodel.UserViewModel.Result.Error
import com.wpay.test_dagger.ui.viewmodel.UserViewModel.Result.Loading
import com.wpay.test_dagger.ui.viewmodel.UserViewModel.Result.Success

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(
    userId: Int,
    userName: String,
    userEmail: String,
    navController: NavController,
    viewModel: UserViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.fetchUserDetails(userId, userName, userEmail)
    }

    val userDetails by viewModel.userDetails.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
        ) {
            when (userDetails) {
                is Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(48.dp)
                    )
                }

                is Success -> {
                    val user = (userDetails as Success<User>).data
                    Column {
                        Text("Name: ${user.name}", style = MaterialTheme.typography.titleMedium)
                        Text("Email: ${user.email}", style = MaterialTheme.typography.bodyMedium)
                    }
                }

                is Error -> Text("Error loading details")
            }
        }
    }
}
