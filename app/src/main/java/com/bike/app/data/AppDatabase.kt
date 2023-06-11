package com.bike.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bike.app.data.model.Bike
import com.bike.app.data.model.Ride

@Database(
    entities = [Bike::class, Ride::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(/*Converters::class*/ /*,ColorConverter::class*/)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bikeDao(): BikeDao
    abstract fun rideDao(): RideDao

    companion object {
        private const val DATABASE_NAME = "bike-app-challenge-db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}