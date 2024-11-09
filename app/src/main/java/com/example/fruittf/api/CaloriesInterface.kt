package com.example.fruittf.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CaloriesInterface {
    @GET("/nutrition")
    suspend fun calories(
        @Query("query") query: String
    ): Response<Model>
}