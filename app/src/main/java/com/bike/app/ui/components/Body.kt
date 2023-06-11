package com.bike.app.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.bike.app.data.model.Destination

@Composable
fun Body(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    destination: Destination,
    orientation: Int,
    onNavigate: (destination: Destination) -> Unit,
    onCreateItem: () -> Unit,
) {
    Row(modifier = modifier) {
        Navigation(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
        )
    }
}