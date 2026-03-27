package com.example.tpandroid.api

import com.example.tpandroid.auth.data.LoginRequest
import com.example.tpandroid.auth.data.RegisterRequest
import com.example.tpandroid.auth.data.ResetPasswordRequest
import com.example.tpandroid.auth.data.User
import retrofit2.http.Body
import retrofit2.http.POST

// L'interface AuthService définit les requêtes API liées à l'authentification des utilisateurs.
// Retrofit utilise cette interface pour générer le code nécessaire à la communication avec le serveur.
interface AuthService {
    // @POST("login") : Cette annotation indique que la méthode effectuera une requête HTTP POST
    // vers le point de terminaison "/login" de l'URL de base de l'API.
    // suspend fun login(...) : Le mot-clé `suspend` signifie que cette fonction est une coroutine,
    // ce qui permet d'effectuer des opérations réseau longues et bloquantes de manière asynchrone
    // sans figer l'interface utilisateur.
    // @Body loginRequest: LoginRequest : L'annotation `@Body` indique que l'objet `loginRequest`
    // sera sérialisé en JSON et inclus dans le corps de la requête POST.
    // ApiResponse<String> : Le type de retour attendu de l'API. C'est une réponse générique
    // qui contient un code de statut, un message et, dans ce cas, un `String` (souvent un jeton d'accès).
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): ApiResponse<String>

    // @POST("signup") : Pour l'enregistrement d'un nouvel utilisateur.
    // La requête est envoyée au point de terminaison "/signup".
    // Le corps de la requête est un objet `RegisterRequest`.
    // Le type de retour attendu est un `ApiResponse` contenant un objet `User`
    // (représentant les données du nouvel utilisateur créé).
    @POST("signup")
    suspend fun signup(@Body registerRequest: RegisterRequest): ApiResponse<User>

    // @POST("reset-password") : Pour réinitialiser le mot de passe d'un utilisateur.
    // La requête est envoyée au point de terminaison "/reset-password".
    // Le corps de la requête est un objet `ResetPasswordRequest` (contenant généralement l'e-mail).
    // Le type de retour est un `ApiResponse` contenant un `String` (souvent le nouveau mot de passe
    // ou un message de confirmation).
    @POST("reset-password")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): ApiResponse<String>
}