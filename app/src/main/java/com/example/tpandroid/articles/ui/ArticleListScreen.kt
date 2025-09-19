// ArticleListScreen.kt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tpandroid.articles.ui.ArticleItem
import com.example.tpandroid.articles.viewModels.ArticleViewModel
import com.example.tpandroid.theme.getCustomGradientBrush

@Composable
fun ArticleListScreen(
    navController: NavController,
    viewModel: ArticleViewModel
) {
    val gradientBrush = getCustomGradientBrush()
    val articles by viewModel.articles.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBrush)
                .padding(16.dp),
            contentPadding = PaddingValues(top = 16.dp),
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Liste des articles",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { navController.navigate("login") },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Déconnexion")
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { viewModel.fetchArticles() },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Charger les articles")
                    }

                    // Bouton pour la création d'articles, bien placé à l'intérieur de la Column.
                    Button(
                        onClick = {
                            viewModel.setArticleToEdit(null)
                            navController.navigate("articleForm")
                        },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Créer un article")
                    }
                }
            }

            items(articles) { article ->
                ArticleItem(
                    article = article,
                    onViewClick = { articleId ->
                        navController.navigate("articleDetails/$articleId")
                    },
                    onEditClick = { articleToEdit ->
                        viewModel.setArticleToEdit(articleToEdit)
                        navController.navigate("articleForm")
                    },
                    onDeleteClick = { articleId ->
                        viewModel.deleteArticle(articleId)
                    }
                )
            }
        }
    }
}