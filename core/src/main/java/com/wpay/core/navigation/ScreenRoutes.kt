package com.wpay.core.navigation

sealed class ScreenRoutes(val route: String) {
    data object UserListScreenScreen : ScreenRoutes("user_list")
    data object UserDetailsScreen : ScreenRoutes("user_details")
    data object SettingsScreen : ScreenRoutes("settings")
    data object HomeScreen : ScreenRoutes("home_screen")
    data object LoginScreen : ScreenRoutes("login_screen")
    data object RegisterScreen : ScreenRoutes("register_screen")
    data object AccountScreen : ScreenRoutes("account_screen")
    data object AppointmentScreen : ScreenRoutes("appointment_screen")
    data object BookAppointmentScreen : ScreenRoutes("book_appointment_screen")
    data object MedHistoryScreenScreen : ScreenRoutes("med_history_screen")

    //Graph Routes
    data object AuthNav : ScreenRoutes("AUTH_NAV_GRAPH")
    data object ConsultationNav : ScreenRoutes("APPOINTMENT_NAV_GRAPH")
    data object MedHistoryNav : ScreenRoutes("MED_HISTORY_NAV_GRAPH")
}