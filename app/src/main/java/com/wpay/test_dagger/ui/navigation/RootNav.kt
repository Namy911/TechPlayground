package com.wpay.test_dagger.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wpay.authentication.ui.navigation.AuthNav
import com.wpay.common.data.BottomNavItem
import com.wpay.common.navigation.ScreenRoutes
import com.wpay.medibook.ui.navigation.AppointmentNav
import com.wpay.test_dagger.ui.screens.BottomNavigationBar
import com.wpay.test_dagger.ui.screens.SettingsScreen
import com.wpay.test_dagger.ui.screens.UserDetailsScreen
import com.wpay.test_dagger.ui.screens.UserListScreen
import com.wpay.test_dagger.ui.viewmodel.UserViewModel
import com.wpay.userstatistics.viewmodel.StatisticsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNav(userViewModel: UserViewModel, statisticsViewModel: StatisticsViewModel) {
    val navController = rememberNavController()

    val bottomNavScreens = listOf(
        BottomNavItem.Consultation,
        BottomNavItem.Settings,
        BottomNavItem.Profile,
    )
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    val itemBar = when (currentDestination?.route) {
        ScreenRoutes.BookAppointmentScreen.route -> BottomNavItem.Consultation
        else -> bottomNavScreens.find { it.route == currentDestination?.route }
    }

    val showTitle = when (currentDestination?.route) {
        ScreenRoutes.AppointmentScreen.route -> false
        else -> true
    }

    Scaffold(
        topBar = {
            if (showTitle && itemBar != null) {
                TopAppBar(title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = (-16).dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(itemBar.labelId),
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                })
            }
        },
        bottomBar = {
            if (itemBar != null) {
                BottomNavigationBar(navController, bottomNavScreens)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.ConsultationNav.route,
            modifier = Modifier.padding(innerPadding)
        ) {
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
                SettingsScreen(navController, userViewModel) {
                    finishAndNavigateBack(navController)
                }
            }

            AuthNav(
                navController = navController,
                onNavigateBack = { finishAndNavigateBack(navController) },
                handleExit = { handleExit(navController) },
            )

            AppointmentNav(navController)
        }
    }
}

fun finishAndNavigateBack(navController: NavHostController) {
    if (navController.previousBackStackEntry == null) {
        navController.navigate(ScreenRoutes.ConsultationNav.route) {
            launchSingleTop = true
            popUpTo(ScreenRoutes.AuthNav.route) { inclusive = true }
        }
    } else {
        navController.popBackStack()
    }
}

fun handleExit(navController: NavHostController) {
    if (navController.previousBackStackEntry != null) {
        navController.popBackStack()
    } else {
        navController.navigate(ScreenRoutes.ConsultationNav.route)
    }
}


