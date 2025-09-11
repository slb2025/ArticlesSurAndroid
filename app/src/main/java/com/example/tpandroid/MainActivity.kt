package com.example.tpandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tpandroid.articles.ui.ArticleDetailScreen
import com.example.tpandroid.articles.ui.ArticleListScreen
import com.example.tpandroid.auth.ui.ForgotPasswordScreen
import com.example.tpandroid.auth.ui.LoginScreen
import com.example.tpandroid.auth.ui.RegisterScreen
import com.example.tpandroid.articles.viewModels.ArticleViewModel

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
                    val articleViewModel: ArticleViewModel = viewModel()

                    ArticleListScreen(navController = navController, viewModel = articleViewModel)
                }

                composable(
                    route = "articleDetails/{articleId}",
                    arguments = listOf(navArgument("articleId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val articleId = backStackEntry.arguments?.getString("articleId")
                    val articleViewModel: ArticleViewModel = viewModel()
                    ArticleDetailScreen(
                        articleId = articleId,
                        viewModel = articleViewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}