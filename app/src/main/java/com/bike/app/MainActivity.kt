package com.bike.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.bike.app.home.Home
import com.bike.app.ui.theme.BikeAppChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BikeAppChallengeTheme {
                Home(
                    modifier = Modifier.fillMaxSize(),
                    orientation = LocalConfiguration.current.orientation
                )
            }
        }
    }
}
