package com.wpay.common.navigation

sealed class ScreenRoutes(val route: String) {
    data object UserListScreenScreen : ScreenRoutes("user_list")
    data object UserDetailsScreen : ScreenRoutes("user_details")
    data object SettingsScreen : ScreenRoutes("settings")
    data object LoginScreen : ScreenRoutes("login_screen")
    data object RegisterScreen : ScreenRoutes("register_screen")
    data object AccountScreen : ScreenRoutes("account_screen")

    //Graph Routes
    data object AuthNav : ScreenRoutes("AUTH_NAV_GRAPH")
}