package com.wpay.common.navigation

sealed class ScreenRoutes(val route: String) {
    data object UserListScreenScreen : ScreenRoutes("user_list")
    data object UserDetailsScreen : ScreenRoutes("user_details")
    data object SettingsScreen : ScreenRoutes("settings")

    //Graph Routes
}