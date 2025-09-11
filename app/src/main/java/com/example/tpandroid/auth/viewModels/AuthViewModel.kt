package com.example.tpandroid.auth.viewModels

class AuthViewModel {
    // Modèles pour les corps de requêtes
    data class LoginRequest(val email: String, val password: String)
    data class SignupRequest(val user: User)
    data class ResetPasswordRequest(val email: String)

    // Modèle de données pour l'utilisateur
    data class User(val email: String, val password: String)

    // Modèles pour les réponses de l'API
    data class ApiResponse<T>(
        val code: Int,
        val message: String,
        val data: T
    )

    data class LoginResponseData(val token: String)
    data class SignupResponseData(val user: User)
    data class ResetPasswordResponseData(val newPassword: String)
}