package com.example.fruittf.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CaloriesInterface {
    @GET("nutrition")
    suspend fun calories(
        @Header("x-api-key") apiKey: String = Utils.key,
        @Query("query") query: String
    ): Response<Model>
}