package com.example.tpandroid.auth.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.auth.data.LoginRequest
import com.example.tpandroid.common.AppAlertHelpers
import com.example.tpandroid.common.AppProgressHelpers
import com.example.tpandroid.auth.data.AuthHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// La classe LoginViewModel est un ViewModel qui gère la logique de l'écran de connexion.
// Elle gère l'état des champs de saisie, l'état de l'interface utilisateur (chargement,
// navigation) et la logique de l'appel d'API pour l'authentification.
class LoginViewModel : ViewModel() {
    // _email et email : Un MutableStateFlow privé pour l'e-mail de l'utilisateur
    // et sa version publique en lecture seule (StateFlow) pour que la vue puisse
    // observer les changements.
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    // _password et password : De la même manière, gèrent l'état du mot de passe.
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    // _navigateToArticles et navigateToArticles : Gèrent l'état de navigation.
    // L'UI observe cette propriété pour savoir quand elle doit naviguer vers l'écran des articles.
    private val _navigateToArticles = MutableStateFlow(false)
    val navigateToArticles: StateFlow<Boolean> = _navigateToArticles.asStateFlow()

    // Méthodes pour mettre à jour les valeurs des StateFlows à partir de l'UI.
    fun setEmail(newEmail: String) { _email.value = newEmail }
    fun setPassword(newPassword: String) { _password.value = newPassword }

    // Méthode pour réinitialiser l'état de navigation. Elle est appelée une fois
    // que la navigation a eu lieu pour éviter les navigations multiples.
    fun onNavigationHandled() {
        _navigateToArticles.value = false
    }

    // Fonction principale pour gérer la connexion.
    fun login() {
        // Lance une coroutine pour exécuter l'appel réseau en arrière-plan.
        viewModelScope.launch {
            // Affiche un indicateur de chargement.
            AppProgressHelpers.show("Connexion en cours...")
            try {
                // Appelle la méthode de connexion de la classe utilitaire AuthHelper
                // et lui passe l'e-mail et le mot de passe actuels.
                val response = AuthHelper.login(LoginRequest(email.value, password.value))

                // Vérifie si la connexion a réussi. Le code "200" et des données non nulles
                // dans la réponse indiquent le succès.
                if (response.code == "200" && response.data != null) {
                    // Si la connexion réussit, le jeton d'authentification est stocké.
                    AuthHelper.storeToken(response.data)
                    // Affiche un message de succès à l'utilisateur.
                    AppAlertHelpers.show(response.message)
                    // Change l'état de navigation pour déclencher la navigation dans l'UI.
                    _navigateToArticles.value = true
                } else {
                    // Si la connexion échoue, affiche le message d'erreur de l'API.
                    AppAlertHelpers.show(response.message)
                }
            } catch (e: Exception) {
                // Intercepte les erreurs (par exemple, de connexion réseau) et affiche un message.
                AppAlertHelpers.show("Erreur de connexion : ${e.message}")
            } finally {
                // Masque l'indicateur de chargement, que la connexion ait réussi ou échoué.
                AppProgressHelpers.close()
            }
        }
    }
}