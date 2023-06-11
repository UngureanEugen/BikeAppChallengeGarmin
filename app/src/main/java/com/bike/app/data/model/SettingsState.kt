package com.bike.app.data.model

data class SettingsState(
    val units: String = "ml",
    val serviceReminder: Int = 100,
    val defaultBike: Long = -1L
)
