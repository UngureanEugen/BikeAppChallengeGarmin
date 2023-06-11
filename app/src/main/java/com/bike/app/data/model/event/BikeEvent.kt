package com.bike.app.data.model.event

import com.bike.app.data.model.Bike

sealed class BikeEvent {
    class EditBike(val bike: Bike) : BikeEvent()
    class DeleteBike(val bike: Bike) : BikeEvent()
    class ShowPopup(val bike: Bike) : BikeEvent()
    class DismissPopup(val bike: Bike) : BikeEvent()
    class Show(val bike: Bike) : BikeEvent()
}