package com.example.hython6.data

data class CategoryResponse(
    val category: String,
    val contents: List<String> = emptyList()
)

data class SummaryResponse(
    val completed_count: Int,
    val not_completed_count: Int
)