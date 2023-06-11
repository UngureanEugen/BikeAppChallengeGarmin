package com.bike.app.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class BikeWithRides(
    @Embedded val bike: Bike,
    @Relation(
        parentColumn = "id",
        entityColumn = "bikeId"
    )
    val rides: List<Ride>
)