package com.wpay.medibook.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.wpay.core.navigation.ScreenRoutes
import com.wpay.medibook.ui.screens.book_consultation.BookAppointmentScreen
import com.wpay.medibook.ui.screens.consultation.AppointmentScreen

fun NavGraphBuilder.AppointmentNav(
    navController: NavHostController,
) {
    navigation(
        startDestination = "${ScreenRoutes.AppointmentScreen.route}/1",
        route = ScreenRoutes.ConsultationNav.route
    ) {
        composable(
            route = "${ScreenRoutes.AppointmentScreen.route}/{userId}", arguments = listOf(
                navArgument("userId") { type = NavType.IntType },
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            AppointmentScreen(userId, navigation = navController)
        }
        composable(
            route = "${ScreenRoutes.BookAppointmentScreen.route}/{activeFormId}", arguments = listOf(
                navArgument("activeFormId") {
                    type = NavType.IntType
                })
        ) { backStackEntry ->
            val activeFormId = backStackEntry.arguments?.getInt("activeFormId") ?: 1
            BookAppointmentScreen(activeFormId = activeFormId, navController = navController)
        }
    }
}