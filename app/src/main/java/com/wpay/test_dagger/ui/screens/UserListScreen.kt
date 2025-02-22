package com.wpay.test_dagger.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.wpay.common.navigation.ScreenRoutes
import com.wpay.common.util.Result
import com.wpay.test_dagger.data.model.User
import com.wpay.test_dagger.ui.viewmodel.UserViewModel
import com.wpay.userstatistics.service.SessionTrackingService
import com.wpay.userstatistics.service.SessionTrackingService.Companion.createSessionTrackingIntent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(viewModel: UserViewModel, navController: NavHostController) {
    val context = LocalContext.current

    val usersState by viewModel.users.collectAsState(initial = Result.Loading)

    LaunchedEffect(Unit) {
        startSessionTracking(context, "1",ScreenRoutes.UserListScreenScreen.route)
    }

    DisposableEffect(Unit) {
        onDispose {
            stopSessionTracking(context)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Fake Users") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                viewModel.addUserOnFabClick(User(0, "New User", "newuser@example.com"))
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add User")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (usersState) {
                is Result.Loading -> {
                    CircularProgressIndicator()
                }
                is Result.Success -> {
                    val listOfUser = (usersState as Result.Success).data
                    UserList(
                        users = listOfUser,
                        navController = navController
                    )
                }

                is Result.Error -> {
                    Text("Error: ${(usersState as Result.Error).message}")
                }
            }
        }
    }
}

@Composable
fun UserList(users: List<User>, navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(users) { user ->
            UserCard(user, navController)
        }
    }
}

@Composable
fun UserCard(user: User, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("${ScreenRoutes.UserDetailsScreen.route}/${user.id}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = user.name, style = MaterialTheme.typography.titleMedium)
            Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

fun startSessionTracking(context: Context, userId: String, screenName: String) {
    val intent = createSessionTrackingIntent(context, userId, screenName)
    context.startService(intent)
}

fun stopSessionTracking(context: Context) {
    val intent = Intent(context, SessionTrackingService::class.java)
    context.stopService(intent)
}
