package com.bike.app.data.model.state

import com.bike.app.data.model.Bike
import java.util.Date

data class AddRideState(
    val title: String = "",
    val bike: Bike? = null,
    val bikes: List<Bike> = emptyList(),
    val distance: Int = 0,
    val duration: Int = 1000,
    val date: Date = Date(),
    val showBikePicker: Boolean = false
)
