package com.bike.app.ride

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.bike.app.R
import com.bike.app.data.model.Destination
import com.bike.app.data.model.graph.GraphData
import com.bike.app.data.model.graph.GraphsDataFactory
import com.bike.app.ui.components.graph.ColumnChart

@Composable
fun RidesChartCell(
    total: Int = 2748,
    entries: List<GraphData> = GraphsDataFactory.makeChartData()
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.small)
            .padding(16.dp)
            .aspectRatio(1f)
            .background(Color(0xff222c48), shape = MaterialTheme.shapes.small),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.padding(8.dp),
                painter = painterResource(id = R.drawable.icon_stats),
                contentDescription = null,
                tint = Color.White
            )
            Text(text = "All Rides Statistics", color = Color.White)
        }
        if(entries.isNotEmpty()) {
            ColumnChart(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(
                        Color(0xff222c48), shape = MaterialTheme.shapes.small
                    )
                    .border(
                        BorderStroke(1.dp, Color(0xff5a6c9e))
                    ),
                entries
            )
        }
        Text(text = "Total ${total}KM", color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_RidesChartCell() {
    MaterialTheme {
        RidesChartCell()
    }
}