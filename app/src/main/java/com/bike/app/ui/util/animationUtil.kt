package com.bike.app.ui.util

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TweenSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.bike.app.data.model.graph.GraphData

@Composable
fun configureAnimation(
    chartData: List<GraphData>
): Animatable<Float, AnimationVector1D> {
    val transitionAnimation = remember(chartData) {
        Animatable(
            initialValue = 0f
        )
    }
    LaunchedEffect(chartData) {
        transitionAnimation.animateTo(
            1f,
            animationSpec = TweenSpec(durationMillis = 750)
        )
    }
    return transitionAnimation
}