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
    val articleToEdit by viewModel.articleToEdit.collectAsState()

    // Les états du formulaire sont définis.
    val idState = remember { mutableStateOf("") }
    val titleState = remember { mutableStateOf("") }
    val descState = remember { mutableStateOf("") }
    val authorState = remember { mutableStateOf("") }
    val imageUrlState = remember { mutableStateOf("") }

    // Utiliser LaunchedEffect pour synchroniser les états locaux du formulaire
    // avec les données du ViewModel (articleToEdit).
    // Ce bloc de code s'exécute à chaque fois que la valeur de 'articleToEdit' change.
    LaunchedEffect(key1 = articleToEdit) {
        articleToEdit?.let {
            // Si articleToEdit n'est pas null, nous sommes en mode modification.
            // On met à jour les états locaux avec les valeurs de l'article.
            idState.value = it.id.orEmpty()
            titleState.value = it.title.orEmpty()
            descState.value = it.description.orEmpty()
            authorState.value = it.author.orEmpty()
            imageUrlState.value = it.imageUrl.orEmpty()
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            // Le titre s'adapte en fonction de la présence de l'ID.
            text = if (idState.value.isBlank()) "Créer un article" else "Modifier un article",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = titleState.value,
            onValueChange = { titleState.value = it },
            label = { Text("Titre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = descState.value,
            onValueChange = { descState.value = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = authorState.value,
            onValueChange = { authorState.value = it },
            label = { Text("Auteur") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = imageUrlState.value,
            onValueChange = { imageUrlState.value = it },
            label = { Text("URL de l'image") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            val articleToSave = Article(
                // Si l'ID est vide, un nouvel article sera créé (l'ID est null).
                // Sinon, l'article existant sera mis à jour.
                id = if (idState.value.isBlank()) null else idState.value,
                title = titleState.value,
                description = descState.value,
                author = authorState.value,
                imageUrl = imageUrlState.value
            )
            viewModel.saveArticle(articleToSave)
            navController.popBackStack()
        }) {
            Text(text = "Sauvegarder")
        }
    }
}