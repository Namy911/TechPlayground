package com.wpay.common.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.wpay.common.R
import com.wpay.common.navigation.ScreenRoutes

sealed class BottomNavItem(
    val route: String,
    @DrawableRes val icoId: Int,
    @StringRes val labelId: Int
) {
    data object UserList : BottomNavItem(
        ScreenRoutes.UserListScreenScreen.route,
        R.drawable.paper,
        R.string.user_list
    )

    data object Settings : BottomNavItem(
        ScreenRoutes.SettingsScreen.route,
        R.drawable.paper,
        R.string.settings
    )

    data object Test : BottomNavItem(
        ScreenRoutes.LoginScreen.route,
        R.drawable.account,
        R.string.test
    )
}