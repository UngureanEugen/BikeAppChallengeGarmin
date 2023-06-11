package com.bike.app.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.bike.app.Tags.TAG_CHILD_TOP_BAR
import com.bike.app.Tags.TAG_ROOT_TOP_BAR
import com.bike.app.data.model.Destination

@Composable
fun DestinationTopBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigateUp: () -> Unit,
    add: () -> Unit
) {
    if (currentDestination == Destination.Splash) return
    if (currentDestination.isRootDestination) {
        RootDestinationTopBar(
            modifier = modifier.testTag(TAG_ROOT_TOP_BAR),
            currentDestination = currentDestination,
            add = add
        )
    } else {
        ChildDestinationTopBar(
            modifier = modifier.testTag(TAG_CHILD_TOP_BAR),
            title = currentDestination.title,
            onNavigateUp = onNavigateUp
        )
    }
}