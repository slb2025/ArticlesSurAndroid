package com.example.tpandroid.articles.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Article(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "desc") val description: String,
    @Json(name = "date") val date: String,
    @Json(name = "setbacks") val setbacks: String,
    @Json(name = "image_url") val imageUrl: String
)