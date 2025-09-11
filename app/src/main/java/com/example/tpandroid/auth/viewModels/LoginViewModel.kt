package com.example.tpandroid.auth.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.api.RetrofitTools
import com.example.tpandroid.auth.data.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _loginResult = MutableStateFlow<String?>(null)
    val loginResult: StateFlow<String?> = _loginResult.asStateFlow()

    fun setEmail(newEmail: String) { _email.value = newEmail }
    fun setPassword(newPassword: String) { _password.value = newPassword }

    fun login() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitTools.authApi.login(LoginRequest(email.value, password.value))
                _loginResult.value = response.message // Stocke le message pour la popup
                if (response.code == "200" && response.data != null) {
                    // TODO: Stocker le token en cache (SharedPreferences ou DataStore)
                    // Puis, naviguer vers la page des articles
                }
            } catch (e: Exception) {
                _loginResult.value = "Erreur de connexion : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}