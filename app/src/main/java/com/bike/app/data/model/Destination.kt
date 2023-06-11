package com.bike.app.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.bike.app.R

sealed class Destination(
    val path: String,
    val icon: ImageVector? = null,
    val isRootDestination: Boolean = true,
    val activeIcon: Int? = R.drawable.icon_more,
    val inActiveIcon: Int = R.drawable.icon_more
) {

    companion object {
        fun fromString(route: String?): Destination {
            return when (route) {
                Bikes.path -> Bikes
                Rides.path -> Rides
                Settings.path -> Settings
                AddBike.path -> AddBike
                AddRide.path -> AddRide
                Upgrade.path -> Upgrade
                Creation.path -> Creation
                Splash.path -> Splash
                else -> Home
            }
        }
    }

    val title = path.replaceFirstChar {
        it.uppercase()
    }

    object Home : Destination("home")

    object Bikes : Destination(
        "bikes",
        activeIcon = R.drawable.icon_bikes_active,
        inActiveIcon = R.drawable.icon_bikes_inactive
    )

    object Rides : Destination(
        "rides",
        activeIcon = R.drawable.rides_active,
        inActiveIcon = R.drawable.rides_inactive
    )

    object Calendar : Destination("calendar", Icons.Default.DateRange)

    object Settings : Destination(
        "settings", Icons.Default.Settings, isRootDestination = false,
        activeIcon = R.drawable.settings_active,
        inActiveIcon = R.drawable.settings_inactive
    )

    object AddBike : Destination("addBike", Icons.Default.Add, isRootDestination = false)

    object AddRide : Destination("addRide", Icons.Default.Add, isRootDestination = false)

    object Upgrade : Destination("upgrade", Icons.Default.Star, isRootDestination = false)

    object Creation : Destination("creation", isRootDestination = false)


    object Splash : Destination("splash", isRootDestination = false)
}
