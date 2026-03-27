package com.example.tpandroid.auth.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tpandroid.auth.viewModels.ForgotPasswordViewModel
import com.example.tpandroid.theme.getCustomGradientBrush
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: ForgotPasswordViewModel = viewModel()
) {
    val gradientBrush = getCustomGradientBrush()

    val email by viewModel.email.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState() // Assurez-vous d'avoir cette ligne
    val resetResult by viewModel.resetResult.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = resetResult) {
        if (resetResult != null) {
            Toast.makeText(context, resetResult, Toast.LENGTH_LONG).show()
            if (resetResult?.contains("Nouveau mot de passe") == true) {
                navController.navigate("login") {
                    popUpTo("forgotPassword") { inclusive = true }
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush) // Ajout de l'arrière-plan
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Réinitialiser le mot de passe",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { viewModel.setEmail(it) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { viewModel.resetPassword() },
                enabled = !isLoading // Désactive le bouton pendant le chargement
            ) {
                Text("Réinitialiser")
            }
        }

        if (isLoading) {
            CircularProgressIndicator(color = Color.White) // Affiche le chargement
        }
    }
}