package com.bike.app.ride

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bike.app.data.BikesRepository
import com.bike.app.data.model.Bike
import com.bike.app.data.model.event.BikeEvent
import com.bike.app.data.model.state.RidesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RidesViewModel @Inject constructor(private val repository: BikesRepository) : ViewModel() {

    val uiState = MutableStateFlow(RidesState())

    init {
        viewModelScope.launch {
            repository.getRides().collectLatest { bikes ->
                uiState.emit(uiState.value.copy(rides = bikes))
            }
        }
        viewModelScope.launch {
            repository.getTotals().collectLatest {
                uiState.emit(uiState.value.copy(totals = it))
            }
        }
    }

    fun handleEvent(event: BikeEvent) {
        when (event) {
            is BikeEvent.DeleteBike -> delete(event.bike)
            is BikeEvent.DismissPopup -> updatePopup(event.bike, false)
            is BikeEvent.ShowPopup -> updatePopup(event.bike, true)
            else -> Unit
        }
    }

    private fun updatePopup(bike: Bike, shopPopup: Boolean) {
        uiState.value = uiState.value.copy(rideExpandedPopupId = if (shopPopup) bike.id else -1)
    }

    private fun delete(bike: Bike) = viewModelScope.launch {
        repository.deleteBike(bike)
    }

}