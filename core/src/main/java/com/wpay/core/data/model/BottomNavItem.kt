package com.wpay.core.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.wpay.core.R
import com.wpay.core.navigation.ScreenRoutes

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

    data object Consultation : BottomNavItem(
        ScreenRoutes.AppointmentScreen.route,
        R.drawable.calendar_24,
        R.string.user_list
    )

    data object MedHistory : BottomNavItem(
        ScreenRoutes.MedHistoryScreenScreen.route,
        R.drawable.group_20,
        R.string.medical_history
    )


    data object Settings : BottomNavItem(
        ScreenRoutes.SettingsScreen.route,
        R.drawable.paper,
        R.string.settings
    )

    data object Profile : BottomNavItem(
        ScreenRoutes.AccountScreen.route,
        R.drawable.account,
        R.string.profile
    )
}