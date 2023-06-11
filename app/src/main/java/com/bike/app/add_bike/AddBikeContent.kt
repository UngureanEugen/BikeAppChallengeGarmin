package com.bike.app.add_bike


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bike.app.R
import com.bike.app.data.model.BikeType.Companion.bikeTypes
import com.bike.app.data.model.event.AddBikeEvent
import com.bike.app.data.model.event.AddBikeEvent.ColorChanged
import com.bike.app.data.model.event.AddBikeEvent.DismissWheelPicker
import com.bike.app.data.model.event.AddBikeEvent.IsDefaultBikeChanged
import com.bike.app.data.model.event.AddBikeEvent.MissingBikeName
import com.bike.app.data.model.event.AddBikeEvent.NameChanged
import com.bike.app.data.model.event.AddBikeEvent.ShowWheelPicker
import com.bike.app.data.model.event.AddBikeEvent.WheelSizeChanged
import com.bike.app.data.model.state.AddBikeState
import com.bike.app.data.model.state.WheelSizeContentState
import com.bike.app.ui.components.BikeNameInput
import com.bike.app.ui.components.ColorPicker
import com.bike.app.ui.components.OutlineInputWithUnit
import com.bike.app.ui.components.WheelSizeContent
import com.bike.app.ui.components.bike_row.BikeTypesPager
import com.bike.app.ui.components.bike_row.DotsIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddBikeContent(
    state: AddBikeState,
    handleEvent: (event: AddBikeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val columnScrollState = rememberScrollState()
    val pagerState = rememberPagerState(initialPage = state.pagerPosition)

    snapshotFlow {
        pagerState.currentPage
    }.collectAsState(initial = state.pagerPosition).value.let {
        handleEvent(AddBikeEvent.PagerPositionChanged(it))
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0F1727))
    ) {

        Image(
            painter = painterResource(id = R.drawable.bike_picker_placeholder),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = modifier.verticalScroll(columnScrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            ColorPicker(selectedColor = state.color, onColorSelected = {
                handleEvent(ColorChanged(it))
            })

            BikeTypesPager(
                pagerState = pagerState,
                bikeTypes = bikeTypes,
                selectedColor = state.color,
                selectedWheelSize = state.wheelSizeContentState.wheelSize,
                modifier = modifier
            )

            Text(
                text = state.type.displayable,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            DotsIndicator(pageCount = bikeTypes.size, currentPageState = pagerState.currentPage)

            BikeNameInput(modifier, state.name, isError = state.nameError) {
                handleEvent(NameChanged(it))
            }

            WheelSizeContent(state.wheelSizeContentState, modifier = modifier,
                dismiss = {
                    handleEvent(DismissWheelPicker)
                },
                onItemSelected = {
                    handleEvent(WheelSizeChanged(it))
                },
                onPickerRequested = {
                    handleEvent(ShowWheelPicker)
                }
            )

            OutlineInputWithUnit(
                modifier,
                title = stringResource(id = R.string.add_bike_service_due),
                state.serviceDue.toString(),
                isError = state.serviceDueError
            ) {
                val serviceDue =
                    if (it.isEmpty() || it.isBlank() || it.length > 7) 0 else it.toInt()
                handleEvent(AddBikeEvent.ServiceDueChanged(serviceDue))
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
                    .semantics(mergeDescendants = true, {})
                    .clickable {
                        handleEvent(IsDefaultBikeChanged(!state.isDefault))
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = modifier.weight(1f),
                    text = stringResource(id = R.string.add_bike_default_bike),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                )
                Switch(
                    state.isDefault,
                    onCheckedChange = {
                        handleEvent(IsDefaultBikeChanged(it))
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.White,
                        checkedTrackColor = Color.Blue
                    )
                )
            }
            AddBikeButton(modifier, enable = !state.serviceDueError && !state.nameError) {
                if (state.name.isEmpty()) {
                    handleEvent(MissingBikeName)
                } else {
                    handleEvent(AddBikeEvent.Add)
                }
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun Preview_AddBikeContent() {
    MaterialTheme {
        AddBikeContent(
            state = AddBikeState(wheelSizeContentState = WheelSizeContentState()),
            handleEvent = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}