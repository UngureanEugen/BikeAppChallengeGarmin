package com.bike.app.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.bike.app.data.AppDatabase
import com.bike.app.data.BikeDao
import com.bike.app.data.BikesRepository
import com.bike.app.data.RideDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHydrationRepository(
        @ApplicationContext app: Context,
        bikeDao: BikeDao,
        rideDao: RideDao
    ): BikesRepository = BikesRepository(app.dataStore, bikeDao, rideDao)

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideBikeDao(appDatabase: AppDatabase): BikeDao {
        return appDatabase.bikeDao()
    }

    @Provides
    fun provideRideDao(appDatabase: AppDatabase): RideDao {
        return appDatabase.rideDao()
    }
}

private const val PREFERENCES_NAME = "app_bike_challenge_preferences"
private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)