package com.example.tpandroid.articles.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tpandroid.articles.data.Article
import com.example.tpandroid.articles.viewModels.ArticleViewModel

@Composable
fun ArticleFormScreen(navController: NavController, viewModel: ArticleViewModel) {
    // Collectez l'article à éditer si il y en a un
    val articleToEdit by viewModel.articleToEdit.collectAsState()

    // États du formulaire
    val idState = remember { mutableStateOf(articleToEdit?.id ?: "") }
    val titleState = remember { mutableStateOf(articleToEdit?.title ?: "") }
    val descState = remember { mutableStateOf(articleToEdit?.description ?: "") }
    val dateState = remember { mutableStateOf(articleToEdit?.date ?: "") }
    val setbacksState = remember { mutableStateOf(articleToEdit?.setbacks ?: "") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = if (idState.value.isBlank()) "Créer un article" else "Modifier un article",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Champs de saisie
        OutlinedTextField(value = titleState.value, onValueChange = { titleState.value = it }, label = { Text("Titre") })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = descState.value, onValueChange = { descState.value = it }, label = { Text("Description") })
        // ... (Ajoutez les autres champs)

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            val newArticle = Article(
                id = idState.value,
                title = titleState.value,
                description = descState.value,
                date = dateState.value,
                setbacks = setbacksState.value,
                imageUrl = "URL_D'IMAGE_PAR_DÉFAUT" // A gérer cela plus tard
            )
            viewModel.saveArticle(newArticle)
            navController.popBackStack() // Retour à l'écran précédent
        }) {
            Text(text = "Sauvegarder")
        }
    }
}