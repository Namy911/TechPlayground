package com.wpay.core.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.wpay.core.R
import com.wpay.core.navigation.ScreenRoutes

sealed class BottomNavItem(
    val route: String,
    @DrawableRes val icoId: Int = 0,
    @StringRes val labelId: Int = 0,
    val isVisibleTitle: Boolean = true,
) {
    data object BookAppointmentScreen : BottomNavItem(
        ScreenRoutes.BookAppointmentScreen.route
    )

    data object Appointment : BottomNavItem(
        ScreenRoutes.AppointmentScreen.route,
        R.drawable.calendar_24,
        R.string.appointment,
          false
    )

    data object MedHistory : BottomNavItem(
        ScreenRoutes.MedHistoryScreenScreen.route,
        R.drawable.group_20,
        R.string.medical_history
    )

    data object Profile : BottomNavItem(
        ScreenRoutes.AccountScreen.route,
        R.drawable.account,
        R.string.profile
    )
}