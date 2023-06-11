package com.bike.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bike.app.R

@Composable
fun OutlineInputWithUnit(
    modifier: Modifier = Modifier,
    title: String = "Bike Name",
    currentValue: String? = "",
    isError: Boolean = false,
    unit: String = "KM",
    onNameChanged: (name: String) -> Unit
) {
    val borderColor = if (isError) {
        Color.Red
    } else {
        MaterialTheme.colors.onSurface
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(modifier = modifier.padding(bottom = 4.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSecondary
            )
            Icon(
                painter = painterResource(id = R.drawable.icon_required),
                tint = MaterialTheme.colors.onSecondary,
                contentDescription = "Icon required"
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 40.dp)
                .background(MaterialTheme.colors.surface)
                .border(
                    1.dp, color = borderColor,
                    shape = MaterialTheme.shapes.small
                )
        ) {
            TextField(value = currentValue ?: "",
                isError = isError,
                onValueChange = onNameChanged,
                singleLine = true,
                textStyle = MaterialTheme.typography.body1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
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
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .padding(end = 16.dp),
                text = unit,
                textAlign = TextAlign.End
            )
        }
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
fun Preview_OutlineInputWithUnit() {
    MaterialTheme {
        BikeNameInput(
            modifier = Modifier.fillMaxWidth(),
            name = "",
            onNameChanged = { }
        )
    }
}