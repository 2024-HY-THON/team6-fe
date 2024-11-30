package com.example.hython6.data.remote

import com.google.gson.annotations.SerializedName

data class ListResponse(
    @SerializedName(value="isSuccess") val isSuccess: Boolean,
)

data class CategoryResponse(
    val categoryId: Int,
    val user: String,
    val category: String,
    val choose: Boolean,
    val alarmTime: String?,
    val randomHabit: Long?
)

data class DetailResponse(
    val contents: List<String>?,
)