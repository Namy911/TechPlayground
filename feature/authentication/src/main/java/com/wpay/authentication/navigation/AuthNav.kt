package com.wpay.authentication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wpay.common.navigation.ScreenRoutes

fun NavGraphBuilder.AuthNav(
    navController: NavHostController
) {
    navigation(
        startDestination = ScreenRoutes.LoginScreen.route,
        route = ScreenRoutes.AuthNav.route
    ){
        composable(route = ScreenRoutes.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = ScreenRoutes.RegisterScreen.route){
            RegisterScreen(navController)
        }
        composable(route = ScreenRoutes.AccountScreen.route){
            AccountScreen(navController)
        }
    }
}