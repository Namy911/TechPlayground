package com.wpay.test_dagger.ui.screens

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wpay.core.data.model.BottomNavItem

@Composable
fun BottomNavigationBar(navController: NavHostController, bottomNavList: List<BottomNavItem>) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        bottomNavList.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.icoId),
                        contentDescription = stringResource(item.labelId),
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(stringResource(item.labelId)) },
                selected = currentRoute == item.route,
                onClick = {
                    val dynamicRoute = item.route.replace("{userId}", "123")
                    navController.navigate(dynamicRoute) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}