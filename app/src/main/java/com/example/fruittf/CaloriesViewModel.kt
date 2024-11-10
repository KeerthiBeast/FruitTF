package com.example.fruittf

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruittf.api.CaloriesInstance
import com.example.fruittf.api.Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CaloriesViewModel(): ViewModel() {
    private var _getCalories: Model? = null
    private val _calories = MutableStateFlow(_getCalories)
    private val images = mutableMapOf<String, Int>()
    val calories = _calories.asStateFlow()

    private fun setImages() {
        images["Apple"] = R.drawable.apple
        images["Banana"] = R.drawable.banana
        images["Grape"] = R.drawable.grapes
        images["Mango"] = R.drawable.mango
        images["Strawberry"] = R.drawable.strawberry
    }

    fun getImages(fruit: String): Int? {
        setImages()
        return images[fruit]
    }

    fun getCalories(fruit: String) {
        viewModelScope.launch {
           try {
               val response = CaloriesInstance.api.calories(
                   query = fruit
               )
               if(response.isSuccessful) {
                   val responseBody = response.body()
                   _getCalories = responseBody
                   _calories.value = _getCalories
                   Log.d("Success", "Success")
               }
               else {
                   Log.d("Failure", response.code().toString())
               }
           } catch(e: Exception) {
                Log.e("Exception", e.toString())
           }
        }
    }
}