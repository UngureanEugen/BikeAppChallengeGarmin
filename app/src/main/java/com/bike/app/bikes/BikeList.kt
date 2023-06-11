package com.bike.app.bikes

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BikeList(onSuccess: () -> Unit, addBikeNav: () -> Unit) {
    val viewModel = hiltViewModel<BikesViewModel>()
//    viewModel.onSuccess = onSuccess
    MaterialTheme {
        BikeListContent(
            bikesState = viewModel.uiState.collectAsState().value,
            handleEvent = viewModel::handleEvent,
            addBikeNav = addBikeNav
        )
    }
}
