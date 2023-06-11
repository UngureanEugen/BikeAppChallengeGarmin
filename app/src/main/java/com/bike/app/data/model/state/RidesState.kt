package com.bike.app.data.model.state

import com.bike.app.data.RideTotals
import com.bike.app.data.model.BikeWithRides
import com.bike.app.data.model.Ride

data class RidesState(
    val rides: List<Ride> = emptyList(),
    val bikesWithRides: List<BikeWithRides> = emptyList(),
    val totals: List<RideTotals> = emptyList(),
    val rideExpandedPopupId: Int = -1
) {
    fun total(): Int {
        return rides.sumOf { it.distance }
    }
}
