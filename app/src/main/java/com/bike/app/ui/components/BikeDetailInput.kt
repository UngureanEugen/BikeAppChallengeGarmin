package com.bike.app.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bike.app.R

@Composable
fun BikeNameInput(
    modifier: Modifier = Modifier,
    name: String? = "Bike Name",
    isError: Boolean = false,
    onNameChanged: (name: String) -> Unit
) {
    val borderColor = if (isError) {
        Color.Red
    } else {
        MaterialTheme.colors.onSurface
    }

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Row(modifier = modifier.padding(bottom = 4.dp)) {
            Text(
                text = "Bike Name",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSecondary
            )
            Icon(
                painter = painterResource(id = R.drawable.icon_required),
                tint = MaterialTheme.colors.onSecondary,
                contentDescription = "Icon required"
            )
        }
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 40.dp)
                .border(1.dp, color = borderColor, shape = MaterialTheme.shapes.small),
            value = name ?: "",
            isError = isError,
            onValueChange = onNameChanged,
            singleLine = true,
            textStyle = MaterialTheme.typography.body1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onNext = {}
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor = Color.White,
                cursorColor = borderColor
            )
        )
        if (isError) {
            Text(
                modifier = modifier.padding(top = 4.dp),
                text = "Required field",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview_BikeNameInput() {
    MaterialTheme {
        BikeNameInput(
            modifier = Modifier.fillMaxWidth(),
            name = "",
            onNameChanged = { }
        )
    }
}