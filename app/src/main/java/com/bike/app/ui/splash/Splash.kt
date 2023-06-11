package com.bike.app.ui.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bike.app.R
import com.bike.app.data.model.Destination
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        // navigation bar
        systemUiController.isNavigationBarVisible = false

        // status bar
        systemUiController.isStatusBarVisible = false

        // system bars
        systemUiController.isSystemBarsVisible = false
    }

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(durationMillis = 500, easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            })
        )
        delay(1500L)
        navController.navigate(Destination.Home.path)
    })
    Box(modifier = Modifier.fillMaxSize()) {
        val backgroundPainter = painterResource(R.drawable.image_source_unsplash)
        val logoPainter = painterResource(R.drawable.logo)
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = backgroundPainter,
            contentDescription = "Background",
            alignment = Alignment.TopEnd,
            contentScale = ContentScale.FillHeight
        )

        Image(
            modifier = Modifier
                .size(288.dp)
                .scale(scale.value)
                .align(Alignment.Center),
            painter = logoPainter,
            contentDescription = "Logo",
            alignment = Alignment.Center
        )
    }
}