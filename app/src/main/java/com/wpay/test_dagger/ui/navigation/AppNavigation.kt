package com.wpay.test_dagger.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wpay.test_dagger.ui.screns.BottomNavigationBar
import com.wpay.test_dagger.ui.screns.SettingsScreen
import com.wpay.test_dagger.ui.screns.UserDetailsScreen
import com.wpay.test_dagger.ui.screns.UserListScreen
import com.wpay.test_dagger.ui.viewmodel.UserViewModel

@Composable
fun AppNavigation(viewModel: UserViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController,
            startDestination = "user_list",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("user_list") {
                UserListScreen(viewModel, navController)
            }
            composable(
                "user_details/{userName}/{userEmail}/{userId}",
                arguments = listOf(
                    navArgument("userName") { type = NavType.StringType },
                    navArgument("userEmail") { type = NavType.StringType },
                    navArgument("userId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val userName = backStackEntry.arguments?.getString("userName") ?: ""
                val userEmail = backStackEntry.arguments?.getString("userEmail") ?: ""
                val userId = backStackEntry.arguments?.getInt("userId") ?: 1
                UserDetailsScreen(userId, userName, userEmail, navController, viewModel)
            }
            composable("settings") {
                SettingsScreen(navController, viewModel)
            }
        }
    }
}
