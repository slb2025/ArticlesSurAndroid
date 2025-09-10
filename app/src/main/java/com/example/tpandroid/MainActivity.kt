package com.example.tpandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tpandroid.article.Article
import com.example.tpandroid.ui.screens.ArticleListScreen
import com.example.tpandroid.ui.screens.ForgotPasswordScreen
import com.example.tpandroid.ui.screens.LoginScreen
import com.example.tpandroid.ui.screens.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {

                composable("login") {
                    LoginScreen(navController = navController)
                }

                composable("forgotPassword") {
                    ForgotPasswordScreen(navController = navController)
                }

                composable("register") {
                    RegisterScreen(navController = navController)
                }

                composable("articles") {
                    val articles = listOf(
                        Article(
                            "Titre 1",
                            "Description de l'article 1...",
                            "https://via.placeholder.com/150"
                        ),
                        Article(
                            "Titre 2",
                            "Description de l'article 2...",
                            "https://via.placeholder.com/150"
                        ),
                    )
                    ArticleListScreen(articles = articles)
                }
            }
        }
    }
}