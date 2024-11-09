package com.example.fruittf.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CaloriesInstance {
    val api: CaloriesInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Utils.base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CaloriesInterface::class.java)
    }
}