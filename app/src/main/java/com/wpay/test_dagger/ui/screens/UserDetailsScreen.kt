package com.wpay.test_dagger.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wpay.core.util.Result
import com.wpay.test_dagger.ui.viewmodel.UserViewModel
import com.wpay.userstatistics.viewmodel.StatisticsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(
    userId: Int?,
    navController: NavController,
    userViewModel: UserViewModel,
    statisticsViewModel: StatisticsViewModel,
) {

    LaunchedEffect(Unit) {
        if (userId != null) {
            userViewModel.fetchUserDetails(userId)
        }
    }

    LaunchedEffect(Unit) {
        if (userId != null) {
            statisticsViewModel.loadUserSessions(userId)
        }
    }

    val userDetails by userViewModel.userDetails.collectAsState()
    val sessionDurations by statisticsViewModel.sessionDurations.collectAsState()

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
            when (val userDetailsState = userDetails) {
                is Result.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(48.dp)
                    )
                }

                is Result.Success -> {
                    val user = userDetailsState.data
                    Column {
                        Text("Name: ${user.name}", style = MaterialTheme.typography.titleMedium)
                        Text("Email: ${user.email}", style = MaterialTheme.typography.bodyMedium)
                        Text(
                            "Session History",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        if (sessionDurations.isEmpty()) {
                            Text("No session history", style = MaterialTheme.typography.titleMedium)
                        }
                        sessionDurations.forEach { session ->
                            Text("Screen: ${session.screenName}")
                            Text("Duration: ${session.duration} sec | Date: ${session.getFormattedDate()} | Time: ${session.getFormattedTime()} ")
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }

                is Result.Error -> Text("Error loading details")
            }
        }

    }
}

