package com.bike.app.ui.components.bike_row


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun Bike(
    modifier: Modifier = Modifier,
    type: String = "electric",
    wheels: String = "small",
    middleColor: Color = Color.Blue
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .allowHardware(true)
        .crossfade(true)
        .components {
            add(SvgDecoder.Factory())
        }.build()

    val wheelsPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data("file:///android_asset/bikes/bike_${type}_${wheels}_wheels.svg")
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        imageLoader
    )
    val middlePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data("file:///android_asset/bikes/bike_${type}_middle.svg")
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        imageLoader
    )
    val overPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data("file:///android_asset/bikes/bike_${type}_over.svg")
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        imageLoader
    )
    Box(modifier = modifier) {
        Image(
            painter = wheelsPainter,
            contentDescription = null,
            modifier = Modifier,
            alignment = Alignment.TopCenter
        )
        Image(
            painter = middlePainter,
            contentDescription = null,
            modifier = Modifier,
            alignment = Alignment.TopCenter, colorFilter = ColorFilter.tint(middleColor)
        )
        Image(
            painter = overPainter,
            contentDescription = null,
            modifier = Modifier,
            alignment = Alignment.TopCenter
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_Bike() {
    MaterialTheme {
        Bike(
            modifier = Modifier,
        )
    }
}