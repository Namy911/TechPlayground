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
import com.wpay.common.data.BottomNavItem
import com.wpay.common.navigation.ScreenRoutes
import com.wpay.test_dagger.ui.screens.BottomNavigationBar
import com.wpay.test_dagger.ui.screens.SettingsScreen
import com.wpay.test_dagger.ui.screens.UserDetailsScreen
import com.wpay.test_dagger.ui.screens.UserListScreen
import com.wpay.test_dagger.ui.viewmodel.UserViewModel
import com.wpay.userstatistics.viewmodel.StatisticsViewModel

@Composable
fun RootNav(userViewModel: UserViewModel, statisticsViewModel: StatisticsViewModel) {
    val navController = rememberNavController()

    val bottomNavScreens = listOf(
        BottomNavItem.UserList,
        BottomNavItem.Settings,
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, bottomNavScreens)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.UserListScreenScreen.route,
            modifier = Modifier.padding(innerPadding)

        )
        {
            composable(ScreenRoutes.UserListScreenScreen.route) {
                UserListScreen(userViewModel, navController)
            }
            composable(
                "${ScreenRoutes.UserDetailsScreen.route}/{userId}",
                arguments = listOf(
                    navArgument("userId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId")
                UserDetailsScreen(
                    userId, navController, userViewModel, statisticsViewModel
                )
            }
            composable(ScreenRoutes.SettingsScreen.route) {
                SettingsScreen(navController, userViewModel)
            }
        }
    }
}

