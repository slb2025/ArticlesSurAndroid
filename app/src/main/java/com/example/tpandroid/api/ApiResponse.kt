package com.example.tpandroid.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    @Json(name = "code") val code: String,
    @Json(name = "message") val message: String,
    @Json(name = "data") val data: T?
)
