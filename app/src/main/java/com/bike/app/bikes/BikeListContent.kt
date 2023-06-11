package com.bike.app.bikes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bike.app.R
import com.bike.app.data.model.Bike
import com.bike.app.data.model.Destination
import com.bike.app.data.model.event.AddBikeEvent
import com.bike.app.data.model.event.BikeEvent
import com.bike.app.data.model.state.BikesState

@Composable
fun BikeListContent(
    modifier: Modifier = Modifier,
    bikesState: BikesState,
    handleEvent: (event: BikeEvent) -> Unit,
    addBikeNav : () -> Unit  = {}
) {
    if(bikesState.bikes.isEmpty()) {
        EmptyBike(
            modifier = Modifier.fillMaxSize(),
            navigate = { addBikeNav() }
        )

    } else {
        LazyColumn(
            modifier = modifier.background(Color.Black).fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(bikesState.bikes, key = { _, bike -> bike.id }) { index, bike ->
                val clickDescription = stringResource(id = R.string.add_bike_default_bike, bike.name)
                BikeInfoContent(
                    modifier = Modifier.clickable(onClickLabel = clickDescription) {
                        handleEvent(BikeEvent.Show(bike))
                    },
                    bike = bike,
                    isExpanded = bike.id == bikesState.bikeExpandedPopupId,
                    handleEvent = handleEvent
                )
            }
        }
    }
}
