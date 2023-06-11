package com.bike.app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ride")
data class Ride(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    val title: String = "Faget MTB Tour",
    val bikeId: Long = -1,
    val bikeType: BikeType = BikeType.Electric,
    val distance: Int = 60,
    val duration: String = "2h 14min",
    val date: String = "",
)
