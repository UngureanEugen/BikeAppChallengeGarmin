package com.bike.app.add_bike

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bike.app.R

@Composable
fun AddBikeButton(
    modifier: Modifier = Modifier,
    label: String = stringResource(id = R.string.add_bike_action),
    enable: Boolean = false,
    onAdd: () -> Unit
) {
    Button(
        modifier = modifier.padding(16.dp).fillMaxWidth().height(48.dp),
        enabled = enable,
        onClick = {
            onAdd()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue,
            disabledBackgroundColor = Color.Blue.copy(alpha = 0.7f))
    ) {
        Text(
            text = label
        )
    }
}

@Preview
@Composable
fun Preview_AuthenticationButton() {
    MaterialTheme {
        AddBikeButton(
            modifier = Modifier.fillMaxWidth(),
            enable = true
        ) {

        }
    }
}