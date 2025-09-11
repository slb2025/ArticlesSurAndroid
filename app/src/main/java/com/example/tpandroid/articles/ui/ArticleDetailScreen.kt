package com.example.tpandroid.articles.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.tpandroid.articles.viewModels.ArticleViewModel
import androidx.navigation.NavController

@Composable
fun ArticleDetailScreen(articleId: String?, viewModel: ArticleViewModel, navController: NavController) {
    val selectedArticle by viewModel.selectedArticle.collectAsState()

    // Charger l'article quand l'écran est affiché
    LaunchedEffect(key1 = articleId) {
        if (articleId != null) {
            viewModel.fetchArticleById(articleId)
        }
    }

    selectedArticle?.let { article ->
        // Afficher les détails de l'article ici
        Text(text = "Détails de l'article : ${article.title}")
    } ?: run {
        // Afficher un indicateur de chargement ou un message d'erreur
        Text(text = "Chargement...")
    }
}