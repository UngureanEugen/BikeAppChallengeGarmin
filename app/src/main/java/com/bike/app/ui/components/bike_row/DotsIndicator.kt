package com.bike.app.ui.components.bike_row

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bike.app.ui.theme.navyBlue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotsIndicator(pageCount: Int = 5, currentPageState: Int) {
    Row(
        Modifier
            .height(50.dp)
            .wrapContentSize()
            .background(Color(0xff20201E), shape = MaterialTheme.shapes.small.copy(all = CornerSize(16.dp))).padding(horizontal = 10.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { iteration ->
            val color = if (currentPageState == iteration) Color.Blue else Color.White
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_DotsIndicator() {
    MaterialTheme {
        DotsIndicator(pageCount = 4, 1)
    }
}