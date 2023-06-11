package com.bike.app.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bike.app.data.model.Destination
import com.bike.app.ui.components.Body
import com.bike.app.ui.components.BottomNavigationBar
import com.bike.app.ui.components.DestinationTopBar

@Composable
fun Home(
    modifier: Modifier = Modifier,
    orientation: Int
) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination by remember {
        derivedStateOf {
            Destination.fromString(navBackStackEntry.value?.destination?.route)
        }
    }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            DestinationTopBar(
                modifier = Modifier.fillMaxWidth(),
                currentDestination = currentDestination,
                onNavigateUp = {
                    navController.popBackStack()
                },
                add = {
                    if (currentDestination == Destination.Bikes) {
                        navController.navigate(Destination.AddBike.path)
                    } else {
                        navController.navigate(Destination.AddRide.path)
                    }
                }
            )
        },
        drawerContent = {
        },
        bottomBar = {
            if (orientation == Configuration.ORIENTATION_PORTRAIT &&
                currentDestination.isRootDestination
            ) {
                BottomNavigationBar(
                    currentDestination = currentDestination,
                    onNavigate = { destination ->
                        navController.navigate(destination.path) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { padding ->
        Body(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            destination = currentDestination,
            navController = navController,
            onCreateItem = {
                navController.navigate(Destination.AddBike.path)
            },
            onNavigate = { destination ->
                navController.navigate(destination.path) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            orientation = orientation,
        )
    }
}

@Preview
@Composable
fun Preview_Home() {
    MaterialTheme {
        Home(modifier = Modifier.fillMaxSize(), orientation = Configuration.ORIENTATION_LANDSCAPE)
    }
}