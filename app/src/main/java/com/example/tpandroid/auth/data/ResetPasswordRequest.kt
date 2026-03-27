package com.example.tpandroid.auth.data

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class ResetPasswordRequest(
    @Json(name = "email") val email: String
)