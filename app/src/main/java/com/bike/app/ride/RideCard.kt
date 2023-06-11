package com.bike.app.ride

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bike.app.R
import com.bike.app.data.model.Ride

@Composable
fun RideCard(
    modifier: Modifier = Modifier,
    ride: Ride,
    isExpanded: Boolean = false,
    handleEvent: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .padding(8.dp).wrapContentSize(),
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp,
        contentColor = Color(0xff222c48)
    ) {
        Icon(
            modifier = Modifier
                .wrapContentSize(align = Alignment.TopEnd)
                .padding(8.dp),
            painter = painterResource(id = R.drawable.icon_more),
            contentDescription = "more action"
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics(mergeDescendants = true) { },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    painter = painterResource(id = R.drawable.icon_bikes_inactive),
                    contentDescription = "more action",
                    tint = Color.White
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = ride.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            Text(
                modifier = Modifier.padding(4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = "Bike ${ride.bikeType}",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                modifier = Modifier.padding(4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = "Distance: ${ride.distance}",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                modifier = Modifier.padding(4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = "Duration: ${ride.duration}",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                modifier = Modifier.padding(4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = "Date: ${ride.date}",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onPrimary
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Preview_RideCard() {
    MaterialTheme {
        RideCard(
            modifier = Modifier.fillMaxWidth(),
            ride = Ride()
        )
    }
}