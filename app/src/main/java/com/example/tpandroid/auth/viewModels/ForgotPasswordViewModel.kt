package com.example.tpandroid.auth.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.api.RetrofitTools
import com.example.tpandroid.auth.data.ResetPasswordRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// ForgotPasswordViewModel est un ViewModel qui gère la logique de l'écran "Mot de passe oublié".
// Il s'occupe de la saisie de l'e-mail, de l'état de chargement et de la réponse de l'API
// pour la réinitialisation du mot de passe.
class ForgotPasswordViewModel : ViewModel() {
    // _email est un MutableStateFlow privé qui contient l'e-mail saisi par l'utilisateur.
    // L'UI peut s'y abonner pour observer les changements.
    private val _email = MutableStateFlow("")
    // email est un StateFlow public en lecture seule pour que l'UI puisse accéder à la valeur
    // sans la modifier directement.
    val email: StateFlow<String> = _email.asStateFlow()

    // _isLoading et isLoading gèrent l'état de chargement pour afficher ou masquer
    // un indicateur de progression sur l'interface utilisateur.
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // _resetResult et resetResult gèrent le résultat de la tentative de réinitialisation.
    // Il peut contenir un message de succès, d'erreur ou le nouveau mot de passe.
    private val _resetResult = MutableStateFlow<String?>(null)
    val resetResult: StateFlow<String?> = _resetResult.asStateFlow()

    // Méthode pour mettre à jour l'e-mail. Elle est appelée depuis la vue chaque
    // fois que l'utilisateur tape du texte dans le champ de saisie.
    fun setEmail(newEmail: String) { _email.value = newEmail }

    // Méthode pour démarrer le processus de réinitialisation du mot de passe.
    fun resetPassword() {
        // Lance une coroutine pour effectuer l'opération réseau sans bloquer le thread principal.
        viewModelScope.launch {
            // Met l'état de chargement à vrai.
            _isLoading.value = true
            try {
                // Appelle l'API de réinitialisation de mot de passe avec l'e-mail de l'utilisateur.
                val response = RetrofitTools.authApi.resetPassword(ResetPasswordRequest(email.value))
                // Met à jour le message de résultat avec le message de la réponse API.
                _resetResult.value = response.message
                // Si la réinitialisation est un succès (code "200"), met à jour le résultat
                // pour afficher le nouveau mot de passe fourni par l'API.
                if (response.code == "200" && response.data != null) {
                    _resetResult.value = "Nouveau mot de passe: ${response.data}"
                }
            } catch (e: Exception) {
                // En cas d'erreur (ex: problème de connexion), affiche un message d'erreur.
                _resetResult.value = "Erreur de réinitialisation: ${e.message}"
            } finally {
                // Met l'état de chargement à faux, que l'appel ait réussi ou échoué.
                _isLoading.value = false
            }
        }
    }
}