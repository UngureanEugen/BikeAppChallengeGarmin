package com.bike.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun BikePopupMenu(
    modifier: Modifier = Modifier,
    entries: List<String> = listOf("Edit", "Delete"),
    expanded: Boolean,
    dismiss: () -> Unit,
    onItemSelected: (item: String) -> Unit,
    onPickerRequested: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(modifier = Modifier, onClick = { onPickerRequested() }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "Localized description", tint = Color.White
            )
        }
        DropdownMenu(
            modifier = Modifier,
            expanded = expanded,
            onDismissRequest = dismiss
        ) {
            entries.map {
                DropdownMenuItem(onClick = { onItemSelected(it) }) {
                    Text(text = it)
                }
            }
        }
    }
}