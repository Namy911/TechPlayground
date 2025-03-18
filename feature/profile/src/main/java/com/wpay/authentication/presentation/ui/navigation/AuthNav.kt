package com.wpay.authentication.presentation.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wpay.authentication.presentation.ui.screens.login.LoginScreen
import com.wpay.authentication.presentation.ui.screens.profile.CreateAccountProfileScreen
import com.wpay.authentication.presentation.ui.screens.registration.RegisterScreen
import com.wpay.core.navigation.ScreenRoutes

fun NavGraphBuilder.AuthNav(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    onNavigateBack: () -> Unit,
    handleExit: () -> Unit,
) {
    navigation(
        startDestination = ScreenRoutes.RegisterScreen.route,
        route = ScreenRoutes.AuthNav.route
    ) {
        composable(route = ScreenRoutes.LoginScreen.route) {
            LoginScreen(navController, snackbarHostState)
        }
        composable(route = ScreenRoutes.RegisterScreen.route) {
            RegisterScreen(navController, snackbarHostState)
        }
        composable(route = ScreenRoutes.AccountScreen.route) {
            CreateAccountProfileScreen(onNavigateBack, handleExit)
        }
    }
}