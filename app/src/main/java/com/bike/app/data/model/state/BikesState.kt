package com.bike.app.data.model.state

import com.bike.app.data.model.Bike

data class BikesState(
    val bikes: List<Bike> = emptyList(),
    val bikeExpandedPopupId: Int = -1
)
