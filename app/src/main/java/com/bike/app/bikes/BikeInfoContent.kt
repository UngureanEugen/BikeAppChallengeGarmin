package com.bike.app.bikes


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bike.app.R
import com.bike.app.data.model.Bike
import com.bike.app.data.model.BikeType
import com.bike.app.data.model.WheelSize
import com.bike.app.data.model.event.BikeEvent
import com.bike.app.ui.components.BikePopupMenu
import com.bike.app.ui.components.bike_row.Bike
import com.bike.app.ui.theme.lightGray

@Composable
fun BikeInfoContent(
    bike: Bike,
    isExpanded: Boolean,
    handleEvent: (event: BikeEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
    ) {

        Image(
            painter = painterResource(id = R.drawable.card_placeholder),
            contentDescription = null,
//            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .clip(MaterialTheme.shapes.small)
        )

        BikePopupMenu(expanded = isExpanded,
            dismiss = {
                handleEvent(BikeEvent.DismissPopup(bike = bike))

            },
            onItemSelected = {
                handleEvent(BikeEvent.EditBike(bike = bike))
            },
            onPickerRequested = {
                handleEvent(BikeEvent.ShowPopup(bike))
            }
        )

        Column(
            modifier = modifier.padding(start = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Bike(
                modifier = Modifier.padding(16.dp),
                type = bike.type.type,
                wheels = bike.wheelSize.type,
                middleColor = Color(bike.color)
            )

            Text(
                modifier = modifier.fillMaxWidth(),
                text = bike.name,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                modifier = modifier.fillMaxWidth(),
                text = "Wheels: ${bike.wheelSize.displayable}",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                modifier = modifier.fillMaxWidth(),
                text = "Service in ${bike.untilServiceDue}",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary
            )

            Slider(
                value = 0.3f,
                modifier = Modifier.background(Color.Transparent)
                    .padding(16.dp)
                    .height(8.dp)
                    .clip(RoundedCornerShape(16.dp)),
                onValueChange = {}
            )

        }

    }

}


@Preview(showBackground = true)
@Composable
fun Preview_BikeInfoContent() {
    MaterialTheme {
        BikeInfoContent(
            bike = Bike(0, "", BikeType.Electric, WheelSize.Small, 48478, 1000),
            handleEvent = {},
            modifier = Modifier.fillMaxSize(), isExpanded = true
        )
    }
}