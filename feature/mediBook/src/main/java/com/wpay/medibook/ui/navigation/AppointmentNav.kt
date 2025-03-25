package com.wpay.medibook.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.wpay.core.navigation.ScreenRoutes
import com.wpay.medibook.data.model.RequestAppointment.UNKNOWN_REQUEST
import com.wpay.medibook.ui.screens.medical_request.BookAppointmentScreen
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
            route = "${ScreenRoutes.BookAppointmentScreen.route}/{actionId}",
            arguments = listOf(
                navArgument("actionId") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("actionId") ?: UNKNOWN_REQUEST.value

            BookAppointmentScreen(
                actionId = id,
                navController = navController
            )
        }
    }
}