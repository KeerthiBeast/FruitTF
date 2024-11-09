package com.example.fruittf

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPage(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title ={
                    Text("Home")
                }
            )
        }
    ) {innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Button(onClick = {navController.navigate(NavName.detection)}) {
               Text("Camera")
            }
            Button(onClick = {navController.navigate(Fruit("Apple"))}) {
                Text("Calories")
            }
        }
    }
}