package com.bike.app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.bike.app.Tags.TAG_BOTTOM_NAVIGATION
import com.bike.app.data.model.Destination
import com.bike.app.data.model.NavigationBarItem.Companion.buildNavigationItems
import com.bike.app.ui.theme.royalBlue
import com.bike.app.ui.theme.slateBlue

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
) {
    BottomNavigation(modifier = modifier.testTag(TAG_BOTTOM_NAVIGATION),
        backgroundColor = slateBlue
    ) {
        buildNavigationItems(
            currentDestination = currentDestination,
            onNavigate = onNavigate
        ).forEach { destination ->
            BottomNavigationItem(
                selectedContentColor = royalBlue,
                unselectedContentColor = Color.White,
                selected = destination.selected,
                icon = { destination.icon() },
                label = { destination.label() },
                onClick = { destination.onClick() }
            )
        }
    }
}

@Preview
@Composable
fun Preview_BottomNavigationBar() {
    MaterialTheme {
        BottomNavigationBar(
            modifier = Modifier.fillMaxWidth(),
            currentDestination = Destination.Rides,
            onNavigate = { }
        )
    }
}