package com.example.tpandroid.api

import com.example.tpandroid.auth.data.LoginRequest
import com.example.tpandroid.auth.data.RegisterRequest
import com.example.tpandroid.auth.data.ResetPasswordRequest
import com.example.tpandroid.auth.data.User
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): ApiResponse<String>

    @POST("signup")
    suspend fun signup(@Body registerRequest: RegisterRequest): ApiResponse<User>

    @POST("reset-password")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): ApiResponse<String>
}