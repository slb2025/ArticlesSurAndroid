package com.example.tpandroid.auth.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.api.RetrofitTools
import com.example.tpandroid.auth.data.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    // StateFlows pour tous les champs du formulaire
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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _registerResult = MutableStateFlow<String?>(null)
    val registerResult: StateFlow<String?> = _registerResult.asStateFlow()

    // Fonctions pour mettre à jour les états
    fun setEmail(newEmail: String) { _email.value = newEmail }
    fun setPassword(newPassword: String) { _password.value = newPassword }
    fun setPasswordConfirm(newPasswordConfirm: String) { _passwordConfirm.value = newPasswordConfirm }
    fun setPseudo(newPseudo: String) { _pseudo.value = newPseudo }
    fun setCityCode(newCityCode: String) { _cityCode.value = newCityCode }
    fun setCity(newCity: String) { _city.value = newCity }
    fun setPhone(newPhone: String) { _phone.value = newPhone }

    fun signup() {
        // Validation basique des mots de passe
        if (password.value != passwordConfirm.value) {
            _registerResult.value = "Les mots de passe ne correspondent pas."
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Créez l'objet RegisterRequest avec toutes les valeurs
                val registerRequest = RegisterRequest(
                    email = email.value,
                    password = password.value,
                    passwordConfirm = passwordConfirm.value,
                    pseudo = pseudo.value,
                    cityCode = cityCode.value,
                    city = city.value,
                    phone = phone.value
                )

                // Appelez l'API d'inscription
                val response = RetrofitTools.authApi.signup(registerRequest)
                _registerResult.value = response.message
                if (response.code == "200") {
                    // L'inscription a réussi
                    // L'UI naviguera vers la page de connexion
                }
            } catch (e: Exception) {
                _registerResult.value = "Erreur d'inscription: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}