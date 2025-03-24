package com.wpay.medibook.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.wpay.core.navigation.ScreenRoutes
import com.wpay.medibook.ui.screens.book_consultation.BookAppointmentScreen
import com.wpay.medibook.ui.screens.consultation.AppointmentScreen
import com.wpay.medibook.viewmodel.AppointmentViewModel

fun NavGraphBuilder.AppointmentNav(
    navController: NavHostController,
) {
    navigation(
        startDestination = ScreenRoutes.AppointmentScreen.route,
        route = ScreenRoutes.ConsultationNav.route
    ) {
        composable(route = ScreenRoutes.AppointmentScreen.route) {
            val appointmentViewModel: AppointmentViewModel = hiltViewModel()
            AppointmentScreen(
                navigation = navController,
                viewModel = appointmentViewModel
            )
        }
        composable(
            route = "${ScreenRoutes.BookAppointmentScreen.route}/{activeFormId}",
            arguments = listOf(
                navArgument("activeFormId") {
                    type = NavType.IntType
                })
        ) { backStackEntry ->
            val activeFormId = backStackEntry.arguments?.getInt("activeFormId") ?: 1
            BookAppointmentScreen(activeFormId = activeFormId, navController = navController)
        }
    }
}