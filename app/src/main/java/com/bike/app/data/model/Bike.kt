package com.bike.app.data.model

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bike.app.ui.theme.darkPink
import com.bike.app.ui.theme.gold
import com.bike.app.ui.theme.limeGreen

@Entity(tableName = "bike")
data class Bike(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    val name: String,
    val type: BikeType,
    val wheelSize: WheelSize,
    val color: Int,
    val untilServiceDue: Int
)/* {
    constructor() : this(0, "", BikeType.Electric, WheelSize.Small, Color.Blue, 1000L, false)
}*/


enum class BikeType(val type: String, val displayable: String, val color: Color) {
    Electric("electric", "E-bike", Color.White),
    Hybrid("hybrid", "City Bike", limeGreen),
    Mtb("mtb", "MTB", gold),
    RoadBike("roadbike", "Road Bike", darkPink);

    companion object {
        fun from(value: String): BikeType {
            return when (value) {
                Electric.type -> Electric
                Hybrid.type -> Hybrid
                Mtb.type -> Mtb
                RoadBike.type -> RoadBike
                else -> RoadBike
            }
        }

        val bikeTypes = listOf(Electric, Hybrid, Mtb, RoadBike)
    }
}

enum class WheelSize(val type: String, val displayable: String) {
    Small("small", "27.5\""), Big("big", "29\"");

    companion object {
        fun from(value: String): WheelSize {
            return when (value) {
                Small.type -> Small
                Big.type -> Big
                else -> Big
            }
        }
    }
}
