package com.bike.app.ride

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bike.app.R
import com.bike.app.data.model.Destination
import com.bike.app.data.model.event.BikeEvent
import com.bike.app.data.model.graph.GraphData
import com.bike.app.data.model.state.RidesState

@Composable
fun AllRides(
    modifier: Modifier = Modifier,
    destination: Destination,
    state: RidesState = RidesState(),
    handleEvent: (event: BikeEvent) -> Unit,
    navToAddRide: () -> Unit = {}
) {
    if (state.rides.isEmpty()) {
        EmptyRides(action = navToAddRide)
    } else {
        Column(
            modifier = modifier
                .testTag(destination.path)
                .background(Color.Black)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            RidesChartCell(
                entries = state.totals.map {
                    GraphData(it.type.displayable, it.total, it.type.color)
                },
                total = state.total()
            )

            LazyColumn(
                modifier = modifier
                    .background(Color.Black)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(state.rides, key = { _, ride -> ride.id }) { index, ride ->
                    val clickDescription =
                        stringResource(id = R.string.add_bike_default_bike, ride.title)
                    RideCard(
                        modifier = Modifier.clickable(onClickLabel = clickDescription) {
//                        handleEvent(BikeEvent.Show(bike))
                        },
                        ride = ride,
                        isExpanded = ride.id == state.rideExpandedPopupId,
//                    handleEvent = handleEvent
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_AllRides() {
    MaterialTheme {
        AllRides(
            modifier = Modifier.fillMaxSize(),
            destination = Destination.Rides,
            handleEvent = {}
        )
    }
}