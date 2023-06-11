package com.bike.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bike.app.ui.theme.chocolate
import com.bike.app.ui.theme.darkGray
import com.bike.app.ui.theme.darkPink
import com.bike.app.ui.theme.dustyBrown
import com.bike.app.ui.theme.gold
import com.bike.app.ui.theme.lavender
import com.bike.app.ui.theme.lightTurquoise
import com.bike.app.ui.theme.limeGreen
import com.bike.app.ui.theme.mediumGreen
import com.bike.app.ui.theme.skyBlue
import com.bike.app.ui.theme.steelBlue

@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    selectedColor: Color,
    onColorSelected: (color: Color) -> Unit,
) {
    val colors = listOf(
        White,
        darkGray,
        mediumGreen,
        darkPink,
        limeGreen,
        skyBlue,
        gold,
        lightTurquoise,
        dustyBrown,
        lavender,
        steelBlue,
        chocolate,
    )
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .background(
                        color = color,
                        shape = CircleShape
                    )
                    .border(
                        width = if (color == selectedColor) 4.dp else 2.dp,
                        color = if (color == selectedColor) {
                            White
                        } else {
                            Color.Transparent
                        },
                        shape = CircleShape
                    )
                    .size(28.dp)
                    .toggleable(color == selectedColor) {
                        onColorSelected(color)
                    }
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun Preview_ColorPicker() {
    ColorPicker(
        modifier = Modifier.wrapContentSize(),
        selectedColor = White,
        onColorSelected = {}
    )
}
