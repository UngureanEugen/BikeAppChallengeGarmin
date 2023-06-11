package com.bike.app.bikes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bike.app.R
import com.bike.app.data.model.Destination
import com.bike.app.ui.theme.royalBlue

@Composable
fun EmptyBike(
    modifier: Modifier = Modifier,
    navigate: () -> Unit
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    Column(
        modifier = modifier
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("file:///android_asset/missing_bike_card.svg")
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                imageLoader
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            alignment = Alignment.TopCenter
        )
        Box {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 48.dp),
                painter = painterResource(id = R.drawable.dotted_line),
                contentDescription = "Empty",
                alignment = Alignment.CenterStart
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .background(Color.Black)
                    .padding(top = 32.dp, start = 48.dp, end = 48.dp),
                fontSize = 18.sp,
                text = stringResource(id = R.string.empty_bike_description),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp).height(56.dp), onClick = { navigate() },
            colors = ButtonDefaults.buttonColors(backgroundColor = royalBlue,
                disabledBackgroundColor = Color.Blue.copy(alpha = 0.7f))
            ) {
            Text(text = "Add Bike")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentArea() {
    MaterialTheme {
        EmptyBike(
            modifier = Modifier.fillMaxSize(),
            {}
        )
    }
}