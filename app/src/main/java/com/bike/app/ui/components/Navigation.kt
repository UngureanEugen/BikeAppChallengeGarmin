package com.bike.app.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bike.app.add_bike.AddBike
import com.bike.app.add_bike.AddRide
import com.bike.app.bikes.BikeList
import com.bike.app.data.model.Destination
import com.bike.app.ride.RidesContent
import com.bike.app.ui.splash.SplashScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.Splash.path
    ) {
        composable(Destination.Splash.path) {
            SplashScreen(navController = navController)
        }
        navigation(
            startDestination = Destination.Bikes.path,
            route = Destination.Home.path,
        ) {
            composable(Destination.Bikes.path) {
                BikeList({}, {
                    navController.navigate(Destination.AddBike.path)
                })
            }

            composable(Destination.Rides.path) {
                RidesContent({}, {
                    navController.navigate(Destination.AddRide.path)
                })
            }

            composable(Destination.AddBike.path) {
                AddBike {
                    navController.navigate(Destination.Bikes.path)
                }
            }

            composable(Destination.AddRide.path) {
                AddRide {
                    navController.navigate(Destination.Rides.path)
                }
            }

        }


        /*        navigation(
                    startDestination = Destination.AddBike.path,
                    route = Destination.AddBike.path
                ) {
                    composable(route = Destination.AddBike.path) {
                        AddBike {
                            navController.navigate(Destination.Bikes.path)
                        }

                        *//*ContentArea(
                    modifier = Modifier.fillMaxSize(),
                    destination = Destination.Add
                )*//*
            }
        }*/

        composable(Destination.Settings.path) {
            ContentArea(
                modifier = Modifier.fillMaxSize(),
                destination = Destination.Settings
            )
        }

        composable(Destination.Upgrade.path) {
            ContentArea(
                modifier = Modifier.fillMaxSize(),
                destination = Destination.Upgrade
            )
        }
    }
}