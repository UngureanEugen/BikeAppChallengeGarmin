package com.bike.app.data.model.event

import androidx.compose.ui.graphics.Color
import com.bike.app.data.model.BikeType
import com.bike.app.data.model.WheelSize

sealed class AddBikeEvent {
    class ColorChanged(val color: Color) : AddBikeEvent()
    class TypeChanged(val type: BikeType) : AddBikeEvent()
    class NameChanged(val name: String) : AddBikeEvent()
    class WheelSizeChanged(val wheelSize: WheelSize) : AddBikeEvent()
    class ServiceDueChanged(val serviceDue: Int) : AddBikeEvent()
    class IsDefaultBikeChanged(val isDefault: Boolean) : AddBikeEvent()
    class PagerPositionChanged(val position: Int) : AddBikeEvent()
    object Add : AddBikeEvent()
    object DismissWheelPicker : AddBikeEvent()
    object ShowWheelPicker : AddBikeEvent()
    object MissingBikeName : AddBikeEvent()
}