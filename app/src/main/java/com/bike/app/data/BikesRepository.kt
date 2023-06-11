package com.bike.app.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.bike.app.data.model.Bike
import com.bike.app.data.model.Ride
import com.bike.app.data.model.SettingsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import java.util.Date

class BikesRepository constructor(
    private val dataStore: DataStore<Preferences>,
    private val bikeDao: BikeDao,
    private val rideDao: RideDao
) {

    object PreferencesKeys {
        val SERVICE_REMINDER = intPreferencesKey("service_reminder")
        val UNIT = stringPreferencesKey("unit")
        val DEFAULT_BIKE = longPreferencesKey("default_bike")
    }

    val preferencesFlow: Flow<SettingsState> = dataStore.data.map { preferences ->
        mapPreferences(preferences)
    }

    private fun mapPreferences(preferences: Preferences): SettingsState {
        val unit = preferences[PreferencesKeys.UNIT] ?: "KM"
        val serviceReminder = preferences[PreferencesKeys.SERVICE_REMINDER] ?: 100
        val defaultBike = preferences[PreferencesKeys.DEFAULT_BIKE] ?: -1L
        return SettingsState(unit, serviceReminder, defaultBike)
    }

    suspend fun update(key: String, value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }


    private suspend fun updateDefaultBikeId(value: Long) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.DEFAULT_BIKE] = value
        }
    }

    suspend fun updateUnits(value: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.UNIT] = value
        }
    }
    /*
        fun today() = hydrationDao.findHydration(dateWithoutHours())

        suspend fun increaseHydration(current: Hydration?, quantity: Int) {
            val hydrationLevel = current?.copy(quantity = current.quantity + quantity)
                ?: Hydration(
                    quantity = quantity,
                    day = dateWithoutHours()
                )
            hydrationDao.insert(hydrationLevel)
        }

        fun getHistory(days: Int): Flow<List<Hydration>> {
            return hydrationDao.getHistory(dateWithoutHours(days))
        }*/

    private fun dateWithoutHours(daysAgo: Int = 0): Date {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        if (daysAgo > 0) {
            calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        }
        return calendar.time
    }

    suspend fun addBike(bike: Bike, isDefault: Boolean) {
        val bikeId = bikeDao.insert(bike)
        if (isDefault) {
            updateDefaultBikeId(bikeId)
        }
    }

    suspend fun addRide(ride: Ride) {
        rideDao.insert(ride)
    }

    fun getBikes() = bikeDao.getBikes()

    fun getBikesWithRides() = bikeDao.getBikesWithRides()

    fun getRides() = rideDao.getRides()

    fun getTotals() = rideDao.getTotals()

    fun getRidesTotal(): Int = rideDao.ridesCount()

    fun getBikesTotal(): Int = bikeDao.bikesCount()

    suspend fun deleteBike(bike: Bike) {
        bikeDao.deleteBike(bike)
    }
}