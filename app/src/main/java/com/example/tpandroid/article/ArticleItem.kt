import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tpandroid.R
import com.example.tpandroid.article.Article

@Composable
fun ArticleItem(article: Article) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)) {

            AsyncImage(
                model = "../res/drawable/article_placeholder.png",
                contentDescription = "image par défaut",
                placeholder = painterResource(R.drawable.article_placeholder),
            )
                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = article.desc,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
        }
    }
}