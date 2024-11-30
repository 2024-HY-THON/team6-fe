package com.example.hython6.data.remote

import com.google.gson.annotations.SerializedName

data class ListResponse(
    @SerializedName(value="isSuccess") val isSuccess: Boolean,
)

data class CategoryResponse(
    val category_id: Int,
    val user: String,
    val category: String,
    val choose: Boolean,
    val alarm_time: String?,
    val random_habit: Long?
)

data class DetailResponse(
    val contents: List<String>?,
)

data class CategoryRequest(
    val category: String
)

data class CategoryAddResponse(
    val message: String,
    val category: CategoryResponse,
)