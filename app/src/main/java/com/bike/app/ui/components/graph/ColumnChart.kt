package com.bike.app.ui.components.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bike.app.data.model.graph.GraphData
import com.bike.app.data.model.graph.GraphsDataFactory
import com.bike.app.ui.util.configureAnimation
import com.bike.app.ui.util.drawDataLabelsOnXAxis
import com.bike.app.ui.util.drawDataTitlesOnXAxis
import com.bike.app.ui.util.drawMatrix
import com.bike.app.ui.util.labelTextPaint
import com.bike.app.ui.util.padding
import com.bike.app.ui.util.sceneLineThickness
import com.bike.app.ui.util.strokeThickness

@Composable
fun ColumnChart(
    modifier: Modifier = Modifier,
    chartData: List<GraphData>
) {
    val maximumValue = chartData.maxOf { it.value }
    val transitionAnimation = configureAnimation(chartData)
    val columnSpacing = with(LocalDensity.current) { 16.dp.toPx() }
    val context = LocalContext.current
    val longestLabel = chartData.maxByOrNull { it.label.length }!!.label
    val labelTextPaint = labelTextPaint(context.resources)
    val labelWidth = labelTextPaint.measureText(longestLabel)

    Canvas(
        modifier = modifier
            .height(320.dp)
            .padding(bottom = 16.dp)
            .fillMaxSize()
    ) {
        val numberOfValues = chartData.count()
        val occupiedSeparatorSpacing = columnSpacing * (numberOfValues + 1) / 1.3f
        val widthSegment =
            (size.width - occupiedSeparatorSpacing - labelWidth) / numberOfValues

        val heightSegment = (size.height - (padding * 2)) / maximumValue

        drawMatrix(labelWidth)

        chartData.forEachIndexed { index, chartData ->
            val drawAtX = columnSpacing + ((columnSpacing + widthSegment) * index)
            val height =
                (heightSegment * chartData.value) * transitionAnimation.value

            drawRoundRect(
                chartData.color,
                topLeft = Offset(
                    x = drawAtX,
                    y = size.height - padding - strokeThickness - height - 10f
                ),
                size = Size(width = widthSegment, height = height),
                cornerRadius = CornerRadius(x = 10f, y = 10f),
            )

            drawRect(
                chartData.color,
                topLeft = Offset(
                    x = drawAtX,
                    y = size.height - padding - sceneLineThickness - 30f
                ),
                size = Size(width = widthSegment, height = 35f),
            )

            drawDataLabelsOnXAxis(
                "${chartData.value}",
                widthSegment + columnSpacing,
                index,
                context.resources,
                labelWidth
            )

            drawDataTitlesOnXAxis(
                chartData.label,
                widthSegment + columnSpacing,
                index,
                context.resources,
                labelWidth + padding
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ColumnChart() {
    MaterialTheme {
        ColumnChart(
            modifier = Modifier.fillMaxSize(),
            GraphsDataFactory.makeChartData()
        )
    }
}