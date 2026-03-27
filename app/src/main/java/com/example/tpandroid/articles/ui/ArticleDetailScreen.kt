package com.example.tpandroid.articles.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tpandroid.articles.viewModels.ArticleViewModel
import com.example.tpandroid.theme.getCustomGradientBrush

@Composable
fun ArticleDetailScreen(articleId: String?, viewModel: ArticleViewModel, navController: NavController) {
    val selectedArticle by viewModel.selectedArticle.collectAsState()
    val gradientBrush = getCustomGradientBrush()

    // Charger l'article quand l'écran est affiché
    LaunchedEffect(key1 = articleId) {
        if (articleId != null) {
            viewModel.fetchArticleById(articleId)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
            .padding(16.dp)
    ) {
        selectedArticle?.let { article ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Titre de l'article
                Text(
                    text = article.title ?: "Titre inconnu",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White
                )

                // Image de l'article
                AsyncImage(
                    model = article.imageUrl,
                    contentDescription = "Image de l'article",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )

                // Description
                Text(
                    text = "Description : ${article.description}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )

                // Auteur
                Text(
                    text = "Auteur : ${article.author}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray
                )


                // Bouton pour revenir à la liste
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                ) {
                    Text("Retour à la liste")
                }
            }
        } ?: run {
            // Afficher un indicateur de chargement si l'article n'est pas encore chargé
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}