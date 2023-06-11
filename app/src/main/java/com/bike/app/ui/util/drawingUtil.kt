package com.bike.app.ui.util

import android.content.res.Resources
import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import com.bike.app.R
import com.bike.app.ui.util.DisplayMetricsUtil.dpToPx

val padding = dpToPx(16f)
val textPadding = dpToPx(16f)
val strokeThickness = dpToPx(1f)
val sceneLineThickness = dpToPx(4f)

fun labelTextPaint(resources: Resources) = Paint().apply {
    textSize = resources.getDimensionPixelSize(R.dimen.label_size).toFloat()
    typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
}

fun titleTextPaint(resources: Resources) = Paint().apply {
    color = resources.getColor(R.color.chart_text_color)
    textSize = resources.getDimensionPixelSize(R.dimen.label_size).toFloat()
    typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
}

fun DrawScope.drawAxis(startPadding: Float = 0f) {
    drawLine(
        strokeWidth = strokeThickness,
        color = Color.Black,
        start = Offset(
            x = startPadding + padding,
            y = size.height - padding
        ),
        end = Offset(
            x = size.width,
            y = size.height - padding
        )
    )
    drawLine(
        strokeWidth = strokeThickness,
        color = Color.Black,
        start = Offset(
            x = startPadding + padding,
            y = size.height - padding
        ),
        end = Offset(
            x = startPadding + padding,
            y = padding
        )
    )
}

fun DrawScope.drawMatrix(startPadding: Float = 0f) {
    val step = (size.height - padding) / 10f
    for (i in 1..10) {
        val drawAtY = step * i
        drawLine(
            strokeWidth = strokeThickness,
            color = Color(0xff5a6c9e),
            start = Offset(
                x = 0f,
                y = drawAtY
            ),
            end = Offset(
                x = size.width,
                y = drawAtY
            )
        )
    }
    drawLine(
        strokeWidth = sceneLineThickness,
        color = Color(0xff5a6c9e),
        start = Offset(
            x = 0f,
            y = size.height - padding
        ),
        end = Offset(
            x = size.width,
            y = size.height - padding
        )
    )
}

fun DrawScope.drawDataLabelsOnXAxis(
    label: String,
    widthSegment: Float,
    index: Int,
    resources: Resources,
    padding: Float
) {
    val drawAtX = textPadding / 3 + sceneLineThickness + (widthSegment * index) + (widthSegment / 2)
    drawIntoCanvas {
        val textPaint = labelTextPaint(resources)
        val textWidth = textPaint.measureText(label)
        val centerX = drawAtX - (textWidth / 2)
        it.nativeCanvas.drawText(
            label,
            centerX,
            size.height - sceneLineThickness - textPadding,
            textPaint
        )
    }
}

fun DrawScope.drawDataTitlesOnXAxis(
    label: String,
    widthSegment: Float,
    index: Int,
    resources: Resources,
    padding: Float
) {
    val drawAtX = textPadding / 3 + sceneLineThickness + (widthSegment * index) + (widthSegment / 2)
    drawIntoCanvas {
        val textPaint = titleTextPaint(resources)
        val textWidth = textPaint.measureText(label)
        val centerX = drawAtX - (textWidth / 2)
        it.nativeCanvas.drawText(
            label,
            centerX,
            size.height + sceneLineThickness + strokeThickness,
            textPaint
        )
    }
}

