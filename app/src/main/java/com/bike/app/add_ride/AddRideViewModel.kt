package com.bike.app.add_bike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bike.app.data.BikesRepository
import com.bike.app.data.model.Bike
import com.bike.app.data.model.BikeType
import com.bike.app.data.model.Ride
import com.bike.app.data.model.event.AddRideEvent
import com.bike.app.data.model.state.AddRideState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddRideViewModel @Inject constructor(private val repository: BikesRepository) :
    ViewModel() {
    lateinit var onSuccess: () -> Unit
    val uiState = MutableStateFlow(AddRideState())

    init {
        viewModelScope.launch {
            repository.getBikes().collectLatest { bikes ->
                uiState.emit(uiState.value.copy(bikes = bikes))
            }
        }
    }

    fun handleEvent(event: AddRideEvent) {
        when (event) {
            AddRideEvent.Add -> addRide()
            is AddRideEvent.BikeChanged -> updateBike(event.bike)
            is AddRideEvent.DateChanged -> updateDate(event.date)
            AddRideEvent.DismissBikePicker -> updateBikePicker(false)
            is AddRideEvent.DistanceChanged -> updateDistance(event.distance)
            is AddRideEvent.DurationChanged -> updateDuration(event.duration)
            AddRideEvent.ShowBikePicker -> updateBikePicker(true)
            is AddRideEvent.TitleChanged -> updateTitle(event.name)
        }
    }

    private fun updateBike(bike: Bike) {
        uiState.value = uiState.value.copy(bike = bike)
    }

    private fun updateDate(date: Long) {
        uiState.value = uiState.value.copy(date = Date(date))
    }

    private fun updateBikePicker(visible: Boolean) {
        uiState.value = uiState.value.copy(showBikePicker = visible)
    }

    private fun updateDistance(distance: Int) {
        uiState.value = uiState.value.copy(distance = distance)
    }

    private fun updateDuration(duration: Int) {
        uiState.value = uiState.value.copy(duration = duration)
    }


    private fun updateTitle(title: String) {
        uiState.value = uiState.value.copy(title = title)
    }


    private fun addRide() = viewModelScope.launch {
        uiState.value.let { state ->
            repository.addRide(
                Ride(
                    title = state.title,
                    bikeId = state.bike?.id?.toLong() ?: -1,
                    bikeType = state.bike?.type ?: BikeType.RoadBike,
                    distance = state.distance,
                    duration = state.duration.toString(),
                    date = state.date.toString()
                )
            )
            onSuccess()
        }
    }
}