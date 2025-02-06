package com.wpay.test_dagger.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wpay.test_dagger.data.model.User
import com.wpay.test_dagger.ui.theme.viewmodel.UserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(viewModel: UserViewModel) {
    val usersState by viewModel.users.collectAsState(initial = UserViewModel.Result.Loading)

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
                is UserViewModel.Result.Loading -> CircularProgressIndicator()
                is UserViewModel.Result.Success -> UserList((usersState as UserViewModel.Result.Success).users)
                is UserViewModel.Result.Error -> Text("Error: ${(usersState as UserViewModel.Result.Error).message}")
            }
        }
    }
}

@Composable
fun UserList(users: List<User>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(users) { user ->
            UserCard(user)
        }
    }
}

@Composable
fun UserCard(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = user.name, style = MaterialTheme.typography.titleMedium)
            Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
