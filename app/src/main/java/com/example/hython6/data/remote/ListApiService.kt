package com.example.hython6.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ListApiService {

    @GET("/category/{user_id}")
    suspend fun getAllCategory(
        @Path("user_id") userId: String,
    ): List<CategoryResponse>
}