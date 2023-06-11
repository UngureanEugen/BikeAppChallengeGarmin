package com.bike.app.data.model.event

import com.bike.app.data.model.Bike

sealed class AddRideEvent {
    class TitleChanged(val name: String) : AddRideEvent()
    class BikeChanged(val bike: Bike) : AddRideEvent()
    class DistanceChanged(val distance: Int) : AddRideEvent()
    class DurationChanged(val duration: Int) : AddRideEvent()
    class DateChanged(val date: Long) : AddRideEvent()
    object Add : AddRideEvent()
    object DismissBikePicker : AddRideEvent()
    object ShowBikePicker : AddRideEvent()
}