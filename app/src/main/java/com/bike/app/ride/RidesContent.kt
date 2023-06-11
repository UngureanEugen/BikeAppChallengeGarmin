package com.bike.app.ride

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.bike.app.data.model.Destination

@Composable
fun RidesContent(onSuccess: () -> Unit, addRide: () -> Unit) {
    val viewModel = hiltViewModel<RidesViewModel>()
//    viewModel.onSuccess = onSuccess
    MaterialTheme {
        AllRides(
            state = viewModel.uiState.collectAsState().value,
            handleEvent = viewModel::handleEvent,
            destination = Destination.Bikes,
            navToAddRide = addRide
        )
    }
}
