package com.wpay.authentication.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wpay.authentication.ui.screens.login.LoginScreen
import com.wpay.authentication.ui.screens.profile.CreateAccountProfileScreen
import com.wpay.authentication.ui.screens.registration.RegisterScreen
import com.wpay.common.navigation.ScreenRoutes

fun NavGraphBuilder.AuthNav(
    navController: NavHostController,
    onNavigateBack: () -> Unit,
    handleExit: () -> Unit,
) {
    navigation(
        startDestination = ScreenRoutes.HomeScreen.route,
        route = ScreenRoutes.AuthNav.route
    ) {
        composable(route = ScreenRoutes.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = ScreenRoutes.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(route = ScreenRoutes.AccountScreen.route) {
            CreateAccountProfileScreen(onNavigateBack, handleExit)
        }
    }
}