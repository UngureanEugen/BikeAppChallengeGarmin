package com.bike.app.data.model

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

class NavigationBarItem(
    val selected: Boolean,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit,
    val label: @Composable () -> Unit
) {

    companion object {
        fun buildNavigationItems(
            currentDestination: Destination,
            onNavigate: (destination: Destination) -> Unit
        ): List<NavigationBarItem> {
            return listOf(
                Destination.Bikes,
                Destination.Rides,
                Destination.Settings
            ).map { destination ->
                NavigationBarItem(
                    selected = currentDestination == destination,
                    label = {
                        Text(text = destination.title)
                    },
                    icon = {
                        destination.activeIcon?.let { image ->
                            val icon = if(currentDestination == destination) {
                                image
                            } else destination.inActiveIcon
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = destination.path
                            )
                        }
                    },
                    onClick = {
                        onNavigate(destination)
                    }
                )
            }
        }
    }

}