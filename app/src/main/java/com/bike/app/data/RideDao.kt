package com.bike.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bike.app.data.model.BikeType
import com.bike.app.data.model.Ride
import kotlinx.coroutines.flow.Flow

@Dao
interface RideDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: Ride): Long

    @Query("SELECT * FROM ride ORDER BY date DESC")
    fun getRides(): Flow<List<Ride>>

    @Query("SELECT SUM(distance) as total, bikeType as type FROM ride GROUP BY bikeType")
    fun getTotals(): Flow<List<RideTotals>>

    @Query("SELECT COUNT(*) FROM ride")
    fun ridesCount(): Int
}

data class RideTotals(val total: Int, val type: BikeType)