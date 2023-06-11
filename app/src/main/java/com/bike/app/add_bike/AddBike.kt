package com.bike.app.add_bike

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AddBike(onSuccess: () -> Unit) {
    val viewModel = hiltViewModel<AddBikeViewModel>()
    viewModel.onSuccess = onSuccess
    MaterialTheme {
        AddBikeContent(
            state = viewModel.uiState.collectAsState().value,
            handleEvent = viewModel::handleEvent
        )
    }
}
