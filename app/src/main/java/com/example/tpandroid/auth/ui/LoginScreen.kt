package com.example.tpandroid.auth.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel // Import manquant
import com.example.tpandroid.auth.viewModels.LoginViewModel
import com.example.tpandroid.theme.customTextFieldColors
import com.example.tpandroid.theme.getCustomGradientBrush // Import manquant

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    // Déclaration de la variable gradientBrush
    val gradientBrush = getCustomGradientBrush()

    // Collecte des états du ViewModel
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val loginResult by viewModel.loginResult.collectAsState()
    val context = LocalContext.current

    // Observe le résultat de la connexion pour afficher la popup et naviguer
    LaunchedEffect(key1 = loginResult) {
        if (loginResult != null) {
            if (loginResult == "Vous êtes connecté(e)") {
                Toast.makeText(context, loginResult, Toast.LENGTH_LONG).show()
                navController.navigate("articles") {
                    popUpTo("login") { inclusive = true }
                }
            } else {
                Toast.makeText(context, loginResult, Toast.LENGTH_LONG).show()
            }
        }
    }

    Box( // Utiliser Box pour afficher le chargement par-dessus le reste
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Connexion",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 32.dp),
                color = Color.White
            )

            OutlinedTextField(
                value = email, // Correction : utiliser 'email' directement
                onValueChange = { viewModel.setEmail(it) },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Unspecified,
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth(),
                colors = customTextFieldColors(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password, // Correction : utiliser 'password' directement
                onValueChange = { viewModel.setPassword(it) },
                label = { Text("Mot de passe") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                colors = customTextFieldColors(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock Icon"
                    )
                }
            )

            Text(
                text = "Mot de passe oublié ?",
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.End)
                    .clickable {
                        navController.navigate("forgotPassword")
                    }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.login() },
                enabled = !isLoading
            ) {
                Text("Connexion")
            }

            TextButton(
                onClick = { navController.navigate("register") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pas encore de compte ? S'inscrire")
            }
        }

        // Utilisation de Box pour le chargement
        if (isLoading) {
            // Un indicateur de chargement au centre de l'écran
            CircularProgressIndicator(color = Color.White)
        }
    }
}