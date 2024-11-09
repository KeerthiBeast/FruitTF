package com.example.fruittf

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


object NavName {
    const val landing = "Landing Page"
    const val detection = "Detection"
}

@Composable
fun Navigation(activity: ComponentActivity, navController: NavHostController, startDest: String) {
    NavHost(navController = navController, startDestination = startDest) {
        composable(NavName.detection) {
            Detect(activity, navController)
        }
        composable(NavName.landing) {
            LandingPage(navController = navController)
        }
        composable<Fruit> {
            val args = it.toRoute<Fruit>()
            FindCalories(fruit = args.name)
        }
    }
}

@Serializable
data class Fruit(
    val name: String
)