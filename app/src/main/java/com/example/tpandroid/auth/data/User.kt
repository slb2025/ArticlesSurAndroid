package com.example.tpandroid.auth.data

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id") val id: String,
    @Json(name = "email") val email: String,
    @Json(name = "pseudo") val pseudo: String,
    @Json(name = "cityCode") val cityCode: String,
    @Json(name = "city") val city: String,
    @Json(name = "phone") val phone: String
)