package com.bike.app.ride

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bike.app.R
import com.bike.app.ui.theme.royalBlue

@Composable
fun EmptyRides(
    modifier: Modifier = Modifier,
    action: () -> Unit
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    Column(
        modifier = modifier
            .background(Color.Black)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val emptyRidesPainter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data("file:///android_asset/dotted_line.svg")
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            imageLoader
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            painter = painterResource(id = R.drawable.empty_rides_placeholder),
            contentDescription = "destination.title",
            alignment = Alignment.CenterStart,
            contentScale = ContentScale.FillWidth
        )
        val bgImg = ContextCompat.getDrawable(
            LocalContext.current, R.drawable.dotted_line_patch
        )


        Image(
            rememberAsyncImagePainter(bgImg),
            modifier = Modifier
                .padding(start = 48.dp)
                .align(Alignment.Start)
                .weight(1f),
            contentDescription = "emptyRides",
            contentScale = ContentScale.FillBounds
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(48.dp), onClick = { action() }, colors = ButtonDefaults.buttonColors(
                backgroundColor = royalBlue,
                disabledBackgroundColor = Color.Blue.copy(alpha = 0.7f)
            )

        ) {
            Text(text = "Add Ride")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_EmptyRides() {
    MaterialTheme {
        EmptyRides(
            modifier = Modifier.fillMaxSize(), {}
        )
    }
}