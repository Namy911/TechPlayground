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
import com.wpay.test_dagger.ui.screens.BottomNavigationBar
import com.wpay.test_dagger.ui.screens.SettingsScreen
import com.wpay.test_dagger.ui.screens.UserDetailsScreen
import com.wpay.test_dagger.ui.screens.UserListScreen
import com.wpay.test_dagger.ui.viewmodel.UserViewModel
import com.wpay.userstatistics.viewmodel.StatisticsViewModel

@Composable
fun AppNavigation(userViewModel: UserViewModel, statisticsViewModel: StatisticsViewModel) {
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
                UserListScreen(userViewModel, navController)
            }
            composable(
                "user_details/{userName}/{userEmail}/{userId}",
                arguments = listOf(
                    navArgument("userId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: 1
                UserDetailsScreen(
                    userId, navController, userViewModel, statisticsViewModel
                )
            }
            composable("settings") {
                SettingsScreen(navController, userViewModel)
            }
        }
    }
}
