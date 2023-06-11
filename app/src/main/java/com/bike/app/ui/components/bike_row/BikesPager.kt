package com.bike.app.ui.components.bike_row

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bike.app.data.model.BikeType
import com.bike.app.data.model.BikeType.Companion.bikeTypes
import com.bike.app.data.model.WheelSize


@ExperimentalFoundationApi
@Composable
fun BikeTypesPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    bikeTypes: List<BikeType> = BikeType.bikeTypes,
    selectedColor: Color,
    selectedWheelSize: WheelSize
) {
    HorizontalPager(
        pageCount = bikeTypes.size,
        state = pagerState,
        beyondBoundsPageCount = 3,
        contentPadding = PaddingValues(horizontal = 48.dp),
        modifier = modifier
            .wrapContentHeight() //and this
            .fillMaxWidth()
    ) { page ->
        Bike(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1.8f),
            type = bikeTypes[page].type,
            wheels = selectedWheelSize.type,
            middleColor = selectedColor
        )
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun Preview_BikesPager() {
    MaterialTheme {
        BikeTypesPager(
            pagerState = rememberPagerState(),
            bikeTypes = bikeTypes,
            selectedColor = Color.White,
            selectedWheelSize = WheelSize.Small
        )
    }
}