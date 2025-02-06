package com.wpay.test_dagger.ui.screns

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.wpay.test_dagger.data.model.User
import com.wpay.test_dagger.ui.viewmodel.UserViewModel
import com.wpay.test_dagger.ui.viewmodel.UserViewModel.Result.Error
import com.wpay.test_dagger.ui.viewmodel.UserViewModel.Result.Loading
import com.wpay.test_dagger.ui.viewmodel.UserViewModel.Result.Success


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(viewModel: UserViewModel, navController: NavHostController) {
    val usersState by viewModel.users.collectAsState(initial = Loading)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Fake Users") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (usersState) {
                is Loading -> CircularProgressIndicator()
                is Success -> UserList(
                    (usersState as Success).data,
                    navController = navController
                )

                is Error -> Text("Error: ${(usersState as Error).message}")
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
                navController.navigate("user_details/${user.name}/${user.email}/${user.id}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = user.name, style = MaterialTheme.typography.titleMedium)
            Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
        }
    }
}