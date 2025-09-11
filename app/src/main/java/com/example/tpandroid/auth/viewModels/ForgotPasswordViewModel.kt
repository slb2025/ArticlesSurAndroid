package com.example.tpandroid.auth.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.api.RetrofitTools
import com.example.tpandroid.auth.data.ResetPasswordRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForgotPasswordViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _resetResult = MutableStateFlow<String?>(null)
    val resetResult: StateFlow<String?> = _resetResult.asStateFlow()

    fun setEmail(newEmail: String) { _email.value = newEmail }

    fun resetPassword() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitTools.authApi.resetPassword(ResetPasswordRequest(email.value))
                _resetResult.value = response.message
                // Si la réinitialisation est un succès, la réponse contient le nouveau mot de passe
                if (response.code == "200" && response.data != null) {
                    _resetResult.value = "Nouveau mot de passe: ${response.data}"
                }
            } catch (e: Exception) {
                _resetResult.value = "Erreur de réinitialisation: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}