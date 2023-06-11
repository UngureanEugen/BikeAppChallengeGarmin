package com.bike.app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bike.app.R
import com.bike.app.data.model.Destination

@Composable
fun RootDestinationTopBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    add: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        actions = {
//            if (currentDestination != Destination.Bikes) {
                IconButton(onClick = { add() }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.cd_more_info)
                    )
                }
//            }
        }
    )
}

@Preview
@Composable
fun Preview_RootDestinationTopBar() {
    MaterialTheme {
        RootDestinationTopBar(
            modifier = Modifier.fillMaxWidth(),
            currentDestination = Destination.Bikes,
            add = { }
        )
    }
}