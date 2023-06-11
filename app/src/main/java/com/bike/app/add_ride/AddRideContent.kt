package com.bike.app.add_ride

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bike.app.R
import com.bike.app.add_bike.AddBikeButton
import com.bike.app.data.model.event.AddRideEvent
import com.bike.app.data.model.state.AddRideState
import com.bike.app.ui.components.BikeDropdown
import com.bike.app.ui.components.OutlineInputWithUnit
import com.bike.app.ui.components.RideDetailInput
import java.util.Date

@Composable
fun AddRideContent(
    modifier: Modifier = Modifier,
    state: AddRideState,
    handleEvent: (event: AddRideEvent) -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0F1727))
    ) {

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.padding(top = 16.dp))
            RideDetailInput(
                modifier,
                label = "Ride Title",
                value = state.title,
                isError = false
            ) {
                handleEvent(AddRideEvent.TitleChanged(it))
            }

            BikeDropdown(
                modifier = modifier,
                bike = state.bike,
                entries = state.bikes,
                expanded = state.showBikePicker,
                dismiss = {
                    handleEvent(AddRideEvent.DismissBikePicker)
                },
                onItemSelected = {
                    handleEvent(AddRideEvent.BikeChanged(it))
                },
                onPickerRequested = {
                    handleEvent(AddRideEvent.ShowBikePicker)
                }
            )

            OutlineInputWithUnit(
                modifier,
                title = stringResource(id = R.string.input_distance),
                state.distance.toString(),
                isError = false
            ) {
                val distance =
                    if (it.isEmpty() || it.isBlank() || it.length > 7) 0 else it.toInt()
                handleEvent(AddRideEvent.DistanceChanged(distance))
            }
            Spacer(modifier = Modifier.padding(top = 16.dp))

            RideDetailInput(
                modifier,
                label = "Duration",
                value = state.duration.toString(),
                isError = false
            ) {
                handleEvent(AddRideEvent.DurationChanged(it.toInt()))
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))
            RideDetailInput(
                modifier,
                label = "Date",
                value = state.date.toString(),
                isError = false
            ) {
                handleEvent(AddRideEvent.DateChanged(Date().time))
            }

            Spacer(modifier = Modifier.weight(1f))

            AddBikeButton(modifier, label = "Add Ride", enable = true) {
                if (state.bike == null) {
                    Toast.makeText( context, "Missing Bike", Toast.LENGTH_LONG).show()
                } else {
                    handleEvent(AddRideEvent.Add)
                }
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun Preview_AddRideContent() {
    MaterialTheme {
        AddRideContent(
            state = AddRideState(),
            handleEvent = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}