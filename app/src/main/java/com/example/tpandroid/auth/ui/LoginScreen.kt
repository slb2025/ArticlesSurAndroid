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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tpandroid.auth.viewModels.LoginViewModel
import com.example.tpandroid.common.AppAlertHelpers
import com.example.tpandroid.common.AppProgressHelpers
import com.example.tpandroid.theme.customTextFieldColors
import com.example.tpandroid.theme.getCustomGradientBrush

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    val gradientBrush = getCustomGradientBrush()

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()

    val isLoading by AppProgressHelpers.isLoading.collectAsState()
    val alertModelData by AppAlertHelpers.alertModelData.collectAsState()
    val navigateToArticles by viewModel.navigateToArticles.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = navigateToArticles) {
        if (navigateToArticles) {
            navController.navigate("articles") {
                popUpTo("login") { inclusive = true }
            }
            viewModel.onNavigationHandled()
        }
    }

    Box(
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
                value = email,
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
                value = password,
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
        if (isLoading) {
            CircularProgressIndicator(color = Color.White)
        }
        AlertDialog()
    }
}

@Composable
fun AlertDialog() {
    val alertModelData by AppAlertHelpers.alertModelData.collectAsState()

    if (alertModelData.isShow) {
        AlertDialog(
            onDismissRequest = { AppAlertHelpers.close() },
            title = { Text(text = "Alerte") },
            text = { Text(text = alertModelData.message) },
            confirmButton = {
                TextButton(onClick = { AppAlertHelpers.close() }) {
                    Text(text = "OK")
                }
            }
        )
    }
}