package com.bike.app.data.model.state

import androidx.compose.ui.graphics.Color
import com.bike.app.data.model.BikeType

data class AddBikeState(
    val type: BikeType = BikeType.Electric,
    val name: String = "",
    val color: Color = Color.White,
    val serviceDue: Int = 1000,
    val isDefault: Boolean = false,
    val pagerPosition: Int = 0,
    val wheelSizeContentState: WheelSizeContentState = WheelSizeContentState(),
    val serviceDueError: Boolean = false,
    val nameError: Boolean = false,
)
