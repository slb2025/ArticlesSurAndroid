package com.example.tpandroid.auth.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.api.RetrofitTools
import com.example.tpandroid.auth.data.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// La classe RegisterViewModel est un ViewModel qui gère la logique de l'écran d'inscription.
// Il gère l'état de tous les champs du formulaire, l'état de chargement, les messages
// de résultat, et la logique de l'appel d'API pour l'inscription.
class RegisterViewModel : ViewModel() {
    // StateFlows pour tous les champs du formulaire.
    // Chaque champ a un `MutableStateFlow` privé pour la modification et un
    // `StateFlow` public en lecture seule pour l'observation par l'UI.
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _passwordConfirm = MutableStateFlow("")
    val passwordConfirm: StateFlow<String> = _passwordConfirm.asStateFlow()

    private val _pseudo = MutableStateFlow("")
    val pseudo: StateFlow<String> = _pseudo.asStateFlow()

    private val _cityCode = MutableStateFlow("")
    val cityCode: StateFlow<String> = _cityCode.asStateFlow()

    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city.asStateFlow()

    private val _phone = MutableStateFlow("")
    val phone: StateFlow<String> = _phone.asStateFlow()

    // _isLoading et isLoading gèrent l'état de chargement pour afficher un
    // indicateur de progression lors de la soumission du formulaire.
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // _registerResult et registerResult gèrent le message de résultat (succès ou erreur)
    // à afficher à l'utilisateur.
    private val _registerResult = MutableStateFlow<String?>(null)
    val registerResult: StateFlow<String?> = _registerResult.asStateFlow()

    // Fonctions pour mettre à jour les états.
    // Chaque fonction est un simple "setter" qui met à jour la valeur du `MutableStateFlow`
    // correspondant à partir de l'entrée de l'UI.
    fun setEmail(newEmail: String) { _email.value = newEmail }
    fun setPassword(newPassword: String) { _password.value = newPassword }
    fun setPasswordConfirm(newPasswordConfirm: String) { _passwordConfirm.value = newPasswordConfirm }
    fun setPseudo(newPseudo: String) { _pseudo.value = newPseudo }
    fun setCityCode(newCityCode: String) { _cityCode.value = newCityCode }
    fun setCity(newCity: String) { _city.value = newCity }
    fun setPhone(newPhone: String) { _phone.value = newPhone }

    // Fonction principale pour soumettre le formulaire d'inscription.
    fun signup() {
        // Validation basique des mots de passe. Si la confirmation ne correspond pas,
        // un message d'erreur est défini et la fonction est arrêtée.
        if (password.value != passwordConfirm.value) {
            _registerResult.value = "Les mots de passe ne correspondent pas."
            return
        }

        // Lance une coroutine pour effectuer l'appel réseau en arrière-plan.
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Créez l'objet RegisterRequest avec toutes les valeurs
                // du formulaire pour l'envoyer à l'API.
                val registerRequest = RegisterRequest(
                    email = email.value,
                    password = password.value,
                    passwordConfirm = passwordConfirm.value,
                    pseudo = pseudo.value,
                    cityCode = cityCode.value,
                    city = city.value,
                    phone = phone.value
                )

                // Appelez l'API d'inscription en utilisant le service d'authentification de Retrofit.
                val response = RetrofitTools.authApi.signup(registerRequest)
                // Met à jour le résultat avec le message de la réponse de l'API.
                _registerResult.value = response.message
                if (response.code == "200") {
                    // L'inscription a réussi. L'UI peut réagir à ce changement d'état.
                }
            } catch (e: Exception) {
                // En cas d'erreur (ex: problème de connexion), met à jour le message de résultat.
                _registerResult.value = "Erreur d'inscription: ${e.message}"
            } finally {
                // S'exécute après le `try` ou le `catch`, masquant l'indicateur de chargement.
                _isLoading.value = false
            }
        }
    }
}