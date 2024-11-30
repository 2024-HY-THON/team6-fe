package com.example.hython6.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ListApiService {

    @GET("/category/{user_id}")
    suspend fun getAllCategory(
        @Path("user_id") userId: String,
    ): List<CategoryResponse>

    @GET("/category/content/{user_id}/{category_id}")
    suspend fun getDetail(
        @Path("user_id") userId: String,
        @Path("category_id") categoryId: Int,
    ): DetailResponse

    @POST("/category/create/{user_id}/")
    suspend fun addCategory(
        @Path("user_id") userId: String,
        @Body category: CategoryRequest,
    ): CategoryAddResponse
}