package com.example.fruittf

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fruittf.api.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindCalories(viewModel: CaloriesViewModel = viewModel(), fruit: String) {
    viewModel.getCalories(fruit)
    val calories by viewModel.calories.collectAsState()
    var searchText by remember { mutableStateOf("") }
    var isEnabled by remember { mutableStateOf(false) }
    val image = viewModel.getImages(fruit)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Calories") })
        }
    ) {innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = image!!),
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()

            ) {
                OutlinedTextField(
                    value = searchText,
                    modifier = Modifier
                        .width(80.dp),
                    onValueChange = { text ->
                        searchText = text
                        if (text.isNotEmpty()) isEnabled = true
                        else isEnabled = false
                    },
                    singleLine = true,
                    shape = MaterialTheme.shapes.extraSmall
                )
                Text(
                    text = "gm",
                    modifier = Modifier
                )
                Button(
                    enabled = isEnabled,
                    onClick = {
                        val searchQuery = "$searchText g $fruit"
                        viewModel.getCalories(searchQuery)
                        searchText = ""
                        isEnabled = false
                    }
                ) {
                    Text("Check")
                }
            }
            LazyColumn() {
                if (calories?.items != null) {
                    items(calories?.items!!) { it ->
                        PrintItems(item = it)
                    }
                }
            }
        }
    }
}

@Composable
fun PrintItems(item: Item) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Name",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)

        )
        Text(
            text = item.name,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Calories",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
        Text(
            text = item.calories.toString(),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Serving Size in grams",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
        Text(
            text = item.serving_size_g.toString(),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Fat Total in grams",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
        Text(
            text = item.fat_total_g.toString(),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Fat Saturated in grams",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
        Text(
            text = item.fat_saturated_g.toString(),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Protein in grams",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
        Text(
            text = item.protein_g.toString(),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Sodium in grams",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
        Text(
            text = item.sodium_mg.toString(),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Potassium in grams",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
        Text(
            text = item.potassium_mg.toString(),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Cholesterol in grams",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
        Text(
            text = item.cholesterol_mg.toString(),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Carbohydrates in grams",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
        Text(
            text = item.carbohydrates_total_g.toString(),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Fiber in grams",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
        Text(
            text = item.fiber_g.toString(),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        )
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Sugar in grams",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
        Text(
            text = item.sugar_g.toString(),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        )
    }
}