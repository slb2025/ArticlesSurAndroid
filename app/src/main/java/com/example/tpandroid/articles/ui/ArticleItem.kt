package com.example.tpandroid.articles.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tpandroid.articles.data.Article

@Composable
fun ArticleItem(
    article: Article,
    onViewClick: (String) -> Unit, // Pour l'action "Voir"
    onEditClick: (Article) -> Unit, // Pour l'action "Éditer"
    onDeleteClick: (String) -> Unit // Pour l'action "Supprimer"
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = "image de l'article",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Affichage des informations de l'article
            Text(
                text = article.title ?: "Titre inconnu",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = article.description ?: "Description inconnu",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Un Row est utilisé pour aligner les boutons d'action horizontalement.
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End, // Alignement à droite
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Bouton "Voir" remplacé par l'icône de loupe
                IconButton(onClick = { onViewClick(article.id.orEmpty()) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Voir l'article", // Description pour l'accessibilité
                        tint = Color.Black
                    )
                }

                // Bouton "Éditer" remplacé par l'icône de stylo
                IconButton(onClick = { onEditClick(article) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Éditer l'article", // Description pour l'accessibilité
                        tint = Color.Black
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Ligne du bouton "Supprimer"
                IconButton(onClick = { onDeleteClick(article.id.orEmpty()) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Supprimer l'article", // Description pour l'accessibilité
                        tint = Color.Red
                    )
                }
            }
        }
    }
}