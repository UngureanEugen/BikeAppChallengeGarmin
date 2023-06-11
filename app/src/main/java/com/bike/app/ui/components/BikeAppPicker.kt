package com.bike.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bike.app.data.model.WheelSize

@Composable
fun BikeAppPicker(
    modifier: Modifier = Modifier,
    currentWheelSize: WheelSize,
    entries: List<WheelSize> = listOf(WheelSize.Small, WheelSize.Big),
    expanded: Boolean,
    dismiss: () -> Unit,
    onItemSelected: (item: WheelSize) -> Unit,
    onPickerRequested: () -> Unit
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClickLabel = currentWheelSize.displayable) { onPickerRequested() }
                .semantics(mergeDescendants = true) { },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .widthIn(min = 60.dp),
                text = currentWheelSize.displayable,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Icon(
                modifier = Modifier.padding(end = 16.dp),
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = Color.White
            )
        }
        DropdownMenu(
            modifier = Modifier,
            expanded = expanded,
            onDismissRequest = dismiss
        ) {
            entries.map {
                DropdownMenuItem(onClick = { onItemSelected(it) }) {
                    Text(text = it.displayable)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_BikeAppPicker() {
    MaterialTheme {
        BikeAppPicker(
            modifier = Modifier,
            currentWheelSize = WheelSize.Small,
            dismiss = {},
            onItemSelected = {},
            onPickerRequested = {},
            expanded = true
        )
    }
}