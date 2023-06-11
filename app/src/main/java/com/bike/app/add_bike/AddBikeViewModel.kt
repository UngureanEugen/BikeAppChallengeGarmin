package com.bike.app.add_bike

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bike.app.data.BikesRepository
import com.bike.app.data.model.Bike
import com.bike.app.data.model.BikeType
import com.bike.app.data.model.BikeType.Companion.bikeTypes
import com.bike.app.data.model.WheelSize
import com.bike.app.data.model.event.AddBikeEvent
import com.bike.app.data.model.state.AddBikeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBikeViewModel @Inject constructor(private val repository: BikesRepository) :
    ViewModel() {
    lateinit var onSuccess: () -> Unit
    val uiState = MutableStateFlow(AddBikeState())

    fun handleEvent(event: AddBikeEvent) {
        when (event) {
            AddBikeEvent.Add -> addBike()
            is AddBikeEvent.ColorChanged -> updateColor(event.color)
            is AddBikeEvent.IsDefaultBikeChanged -> updateIsDefault(event.isDefault)
            is AddBikeEvent.NameChanged -> updateName(event.name)
            is AddBikeEvent.ServiceDueChanged -> updateServiceDue(event.serviceDue)
            is AddBikeEvent.TypeChanged -> updateType(event.type)
            is AddBikeEvent.WheelSizeChanged -> updateWheelSize(event.wheelSize)
            is AddBikeEvent.PagerPositionChanged -> updatePagerPosition(event.position)
            AddBikeEvent.DismissWheelPicker -> updateWheelPicker(false)
            AddBikeEvent.ShowWheelPicker -> updateWheelPicker(true)
            AddBikeEvent.MissingBikeName -> missingBikeName()
        }
    }

    private fun updateWheelPicker(isVisible: Boolean) {
        val wheelSizeContentState = uiState.value.wheelSizeContentState
        viewModelScope.launch {
            uiState.emit(
                uiState.value.copy(
                    wheelSizeContentState = wheelSizeContentState.copy(isExpanded = isVisible)
                )
            )
        }
    }

    private fun updateName(name: String) {
        uiState.value = uiState.value.copy(name = name, nameError = name.isEmpty())
    }

    private fun updateColor(color: Color) {
        uiState.value = uiState.value.copy(color = color)
    }

    private fun updateType(type: BikeType) {
        uiState.value = uiState.value.copy(type = type)
    }

    private fun missingBikeName() {
        uiState.value = uiState.value.copy(nameError = true)
    }

    private fun updateWheelSize(wheelSize: WheelSize) {
        val wheelSizeContentState = uiState.value.wheelSizeContentState
        viewModelScope.launch {
            uiState.emit(
                uiState.value.copy(
                    wheelSizeContentState = wheelSizeContentState.copy(wheelSize = wheelSize)
                )
            )
        }
    }

    private fun updateServiceDue(serviceDue: Int) {
        uiState.value =
            uiState.value.copy(serviceDue = serviceDue, serviceDueError = serviceDue == 0)
    }

    private fun updateIsDefault(isDefault: Boolean) {
        uiState.value = uiState.value.copy(isDefault = isDefault)
    }

    private fun updatePagerPosition(pagerPosition: Int) {
        uiState.value =
            uiState.value.copy(pagerPosition = pagerPosition, type = bikeTypes[pagerPosition])
    }

    private fun addBike() = viewModelScope.launch {
        uiState.value.let {
            val bike = Bike(
                name = it.name,
                type = it.type,
                wheelSize = it.wheelSizeContentState.wheelSize,
                color = it.color.toArgb(),
                untilServiceDue = it.serviceDue
            )
            repository.addBike(bike, it.isDefault)
            onSuccess()
        }
    }
}