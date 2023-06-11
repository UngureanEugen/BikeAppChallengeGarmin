package com.bike.app.ui.components

import com.bike.app.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ChildDestinationTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onNavigateUp: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {
                onNavigateUp()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.cd_navigate_up)
                )
            }
        }
    )
}

@Preview
@Composable
fun Preview_ChildDestinationTopBar() {
    MaterialTheme {
        ChildDestinationTopBar(
            modifier = Modifier.fillMaxWidth(),
            title = "Title",
            onNavigateUp = { }
        )
    }
}