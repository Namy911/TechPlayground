package com.wpay.medhistory.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wpay.core.navigation.ScreenRoutes
import com.wpay.medhistory.ui.screens.MedicalHistoryScreen

fun NavGraphBuilder.MedHistoryNav() {
    navigation(
        startDestination = ScreenRoutes.MedHistoryScreenScreen.route,
        route = ScreenRoutes.MedHistoryNav.route
    ) {
        composable(route = ScreenRoutes.MedHistoryScreenScreen.route) {
            MedicalHistoryScreen()
        }
    }
}