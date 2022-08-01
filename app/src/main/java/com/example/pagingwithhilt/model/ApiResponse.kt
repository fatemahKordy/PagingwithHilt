package com.example.pagingwithhilt.model

import com.squareup.moshi.Json

data class ApiResponse(
    @Json(name = "ad")
    val ad: Ad?=null,
    @Json(name = "data")
    val myData: List<Data>,
    @Json(name = "page")
    val page: Int,
    @Json(name = "per_page")
    val perPage: Int,
    @Json(name = "total")
    val total: Int,
    @Json(name = "total_pages")
    val totalPages: Int
)