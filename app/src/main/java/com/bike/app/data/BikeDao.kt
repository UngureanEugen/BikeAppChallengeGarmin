package com.bike.app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.bike.app.data.model.Bike
import com.bike.app.data.model.BikeWithRides
import kotlinx.coroutines.flow.Flow

@Dao
interface BikeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: Bike): Long

    @Query("SELECT * FROM bike ORDER BY untilServiceDue DESC")
    fun getBikes(): Flow<List<Bike>>

    @Transaction
    @Query("SELECT * FROM BIKE")
    fun getBikesWithRides(): List<BikeWithRides>

    @Query("SELECT COUNT(*) FROM BIKE")
    fun bikesCount(): Int

    @Transaction
    @Query("SELECT * FROM BIKE Where id = :bikeId")
    fun getBikeWithRides(bikeId: Long): List<BikeWithRides>

    @Delete
    suspend fun deleteBike(vararg bike: Bike)
}