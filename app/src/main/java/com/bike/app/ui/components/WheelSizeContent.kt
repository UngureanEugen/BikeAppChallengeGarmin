package com.bike.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bike.app.R
import com.bike.app.data.model.WheelSize
import com.bike.app.data.model.state.WheelSizeContentState

@Composable
fun WheelSizeContent(
    state: WheelSizeContentState,
    modifier: Modifier = Modifier,
    dismiss: () -> Unit,
    onItemSelected: (item: WheelSize) -> Unit,
    onPickerRequested: () -> Unit
) {
    val borderColor = MaterialTheme.colors.onSurface

    Column(modifier = modifier.padding(16.dp)) {
        Row(modifier = modifier.padding(bottom = 4.dp)) {
            Text(
                text = "Wheel Size",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSecondary
            )
            Icon(
                painter = painterResource(id = R.drawable.icon_required),
                tint = MaterialTheme.colors.onSecondary,
                contentDescription = "Icon required"
            )
        }

        BikeAppPicker(
            modifier = modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 40.dp)
                .background(color = MaterialTheme.colors.surface)
                .border(1.dp, color = borderColor, shape = MaterialTheme.shapes.small),
            currentWheelSize = state.wheelSize,
            expanded = state.isExpanded,
            dismiss = dismiss,
            onItemSelected = onItemSelected,
            onPickerRequested = onPickerRequested)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_WheelSizeContent() {
    MaterialTheme {
        WheelSizeContent(
            modifier = Modifier.fillMaxWidth(),
            state = WheelSizeContentState(false, WheelSize.Small),
            dismiss = {},
            onItemSelected = {},
            onPickerRequested = {}
        )
    }
}