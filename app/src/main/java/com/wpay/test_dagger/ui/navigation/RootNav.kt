package com.wpay.test_dagger.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wpay.authentication.presentation.ui.navigation.AuthNav
import com.wpay.core.data.model.BottomNavItem
import com.wpay.core.navigation.ScreenRoutes
import com.wpay.medhistory.ui.navigation.MedHistoryNav
import com.wpay.medibook.ui.navigation.AppointmentNav
import com.wpay.test_dagger.ui.screens.BottomNavigationBar
import com.wpay.test_dagger.ui.screens.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNav() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    val bottomNavScreens = listOf(
        BottomNavItem.Consultation,
        BottomNavItem.Settings,
        BottomNavItem.MedHistory,
        BottomNavItem.Profile,
    )
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    val itemBar = when (currentDestination?.route) {
        ScreenRoutes.BookAppointmentScreen.route -> BottomNavItem.Consultation
        else -> bottomNavScreens.find { it.route == currentDestination?.route }
    }

    val showTitle = when (currentDestination?.route) {
        ScreenRoutes.AppointmentScreen.route -> false
        else -> true
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            if (showTitle && itemBar != null) {
                TopAppBar(title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = (-16).dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(itemBar.labelId),
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                })
            }
        },
        bottomBar = {
            if (itemBar != null) {
                BottomNavigationBar(navController, bottomNavScreens)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.AuthNav.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ScreenRoutes.HomeScreen.route) {
                HomeScreen(navController)
            }
            AuthNav(
                navController = navController,
                snackbarHostState = snackbarHostState,
                onNavigateBack = { finishAndNavigateBack(navController) },
                handleExit = { handleExit(navController) },
            )

            AppointmentNav(navController)
            MedHistoryNav()
        }
    }
}

fun finishAndNavigateBack(navController: NavHostController) {
    if (navController.previousBackStackEntry == null) {
        navController.navigate(ScreenRoutes.ConsultationNav.route) {
            launchSingleTop = true
            popUpTo(ScreenRoutes.AuthNav.route) { inclusive = true }
        }
    } else {
        navController.popBackStack()
    }
}

fun handleExit(navController: NavHostController) {
    if (navController.previousBackStackEntry != null) {
        navController.popBackStack()
    } else {
        navController.navigate(ScreenRoutes.ConsultationNav.route)
    }
}


