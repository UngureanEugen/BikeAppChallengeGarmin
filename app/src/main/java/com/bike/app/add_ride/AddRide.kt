package com.bike.app.add_bike

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.bike.app.add_ride.AddRideContent

@Composable
fun AddRide(onSuccess: () -> Unit) {
    val viewModel = hiltViewModel<AddRideViewModel>()
    viewModel.onSuccess = onSuccess
    MaterialTheme {
        AddRideContent(
            state = viewModel.uiState.collectAsState().value,
            handleEvent = viewModel::handleEvent
        )
    }
}
