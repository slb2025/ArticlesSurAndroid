package com.example.tpandroid.auth.data

import com.example.tpandroid.api.ApiResponse
import com.example.tpandroid.api.RetrofitTools
import com.example.tpandroid.common.AppContextHelper
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

// Singleton utilitaire pour la gestion de l'authentification.
// Ce fichier regroupe les fonctions pour la connexion, l'inscription,
// la réinitialisation du mot de passe et la gestion du token d'authentification.
object AuthHelper {

    // Nom du fichier de préférences partagées où le token sera stocké.
    private const val PREFS_NAME = "auth_prefs"

    // Clé pour le token d'authentification dans les préférences partagées.
    private const val TOKEN_KEY = "auth_token"

    // Effectue une requête de connexion via l'API.
    // @param loginRequest Les informations de connexion (email, mot de passe).
    // @return Une réponse d'API contenant le token d'authentification ou une erreur.
    suspend fun login(loginRequest: LoginRequest): ApiResponse<String> {
        return RetrofitTools.authApi.login(loginRequest)
    }

    // Effectue une requête d'inscription via l'API.
    // @param registerRequest Les informations d'inscription (nom, email, mot de passe).
    // @return Une réponse d'API contenant les informations de l'utilisateur créé ou une erreur.
    suspend fun signup(registerRequest: RegisterRequest): ApiResponse<User> {
        return RetrofitTools.authApi.signup(registerRequest)
    }

    // Effectue une requête de réinitialisation de mot de passe via l'API.
    // @param resetPasswordRequest Les informations pour la réinitialisation (email).
    // @return Une réponse d'API contenant un message de succès ou une erreur.
    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): ApiResponse<String> {
        return RetrofitTools.authApi.resetPassword(resetPasswordRequest)
    }

    // Stocke le token d'authentification dans les SharedPreferences.
    // Cela permet de garder l'utilisateur connecté entre les sessions.
    // @param token Le token d'authentification à stocker.
    fun storeToken(token: String) {
        val sharedPrefs: SharedPreferences = AppContextHelper.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharedPrefs.edit {
            putString(TOKEN_KEY, token)
        }
    }

    // Récupère le token d'authentification depuis les SharedPreferences.
    // @return Le token d'authentification s'il existe, sinon null.
    fun getToken(): String? {
        val sharedPrefs: SharedPreferences = AppContextHelper.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPrefs.getString(TOKEN_KEY, null)
    }

    // Supprime le token d'authentification des SharedPreferences.
    // Cette fonction est typiquement utilisée lors de la déconnexion de l'utilisateur.
    fun clearToken() {
        val sharedPrefs: SharedPreferences = AppContextHelper.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPrefs.edit()) {
            remove(TOKEN_KEY)
            apply()
        }
    }
}