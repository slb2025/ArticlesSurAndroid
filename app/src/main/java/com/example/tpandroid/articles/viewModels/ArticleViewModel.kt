package com.example.tpandroid.articles.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.R
import com.example.tpandroid.api.RetrofitTools
import com.example.tpandroid.articles.data.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.plus

class ArticleViewModel : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles.asStateFlow()

    private val _selectedArticle = MutableStateFlow<Article?>(null)
    val selectedArticle: StateFlow<Article?> = _selectedArticle.asStateFlow()

    private val _articleToEdit = MutableStateFlow<Article?>(null)
    val articleToEdit: StateFlow<Article?> = _articleToEdit.asStateFlow()
    fun fetchArticles() {
        viewModelScope.launch {
            try {
                // Appel API et récupération des données du champ 'data'
                val response = RetrofitTools.apiService.getArticles()
                _articles.value = response.data ?: emptyList()
            } catch (e: Exception) {
                // Gérer les erreurs (réseau, serveur, etc.)
                _articles.value = emptyList()
            }
        }
    }

    fun fetchArticleById(id: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitTools.apiService.getArticleById(id)
                if (response.code == "200") {
                    _selectedArticle.value = response.data
                }
            } catch (e: Exception) {
                // Gérer les erreurs
                _selectedArticle.value = null
            }
        }
    }

    fun setArticleToEdit(article: Article?) {
        _articleToEdit.value = article
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            try {
                val response = RetrofitTools.apiService.saveArticle(article)
                if (response.code == "200") {
                    // Mettez à jour la liste des articles après la sauvegarde réussie
                    fetchArticles()
                    // Réinitialisez l'article à éditer
                    _articleToEdit.value = null
                }
            } catch (e: Exception) {
                // Gérer les erreurs
            }
        }
    }

    fun deleteArticle(articleId: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitTools.apiService.deleteArticle(articleId)
                if (response.code == "200") {
                    // Mettez à jour la liste des articles après la suppression réussie
                    fetchArticles()
                } else {
                    // Réinitialisez l'article à éditer
                    _articleToEdit.value = null
                }
            } catch (e: Exception) {
                // Gérer les erreurs
            }
        }
    }
}

//private fun loadInitialArticles() {
//    val initialArticles = listOf(
//        Article(
//            "Édouard Philippe : l'architecte des grandes réformes",
//            "Premier chef de gouvernement d'Emmanuel Macron, Édouard Philippe a piloté des réformes majeures comme la suppression de l'ISF et la réforme de la SNCF. Son mandat a été marqué par la crise des 'Gilets Jaunes' en 2018 et l'entrée dans la pandémie de COVID-19. Très populaire à son départ, il a su se forger une image d'homme d'État face aux crises successives. Ses déboires incluent l'affaire Benalla et la gestion de la crise des 'Gilets Jaunes', vivement critiquée pour la répression policière.",
//            "21 juin 2017 - 3 juillet 2020",
//            "Crise des 'Gilets Jaunes', affaire Benalla",
//            R.drawable.e_philippe
//        ),
//        Article(
//            "Jean Castex : le 'Monsieur Déconfinement'",
//            "Surnommé le 'Monsieur Déconfinement', Jean Castex a été nommé Premier ministre en pleine crise sanitaire du COVID-19. Son mandat a été entièrement dédié à la gestion de la pandémie, avec la mise en œuvre de mesures de restriction et de soutien économique. Il a également porté la loi 'Climat et résilience' et la loi '3DS'. Son style jugé plus proche et son franc-parler ont marqué son passage à Matignon. Il a été critiqué pour sa gestion des pass sanitaires et des confinements successifs.",
//            "3 juillet 2020 - 16 mai 2022",
//            "Critiques sur la gestion de la crise sanitaire, utilisation des 49.3 pour la loi climat",
//            R.drawable.j_castex
//        ),
//        Article(
//            "Élisabeth Borne : une mandature sous le signe du 49.3",
//            "Élisabeth Borne est devenue la deuxième femme Première ministre de l'histoire de France. Son action a été fortement dominée par la réforme des retraites, qui a provoqué d'importantes manifestations dans tout le pays. Confrontée à une majorité relative à l'Assemblée nationale, elle a eu recours à de multiples reprises à l'article 49.3 de la Constitution pour faire passer ses textes. Ses déboires sont directement liés à l'impopularité de la réforme des retraites et à l'usage controversé du 49.3, qui a suscité de vives critiques de l'opposition et des syndicats.",
//            "16 mai 2022 - 8 janvier 2024",
//            "Réforme des retraites, usage massif du 49.3, contestation sociale",
//            R.drawable.e_borne
//        ),
//        Article(
//            "Gabriel Attal : le plus jeune Premier ministre de la Vᵉ République",
//            "Nommé à seulement 34 ans, Gabriel Attal est le plus jeune Premier ministre de l'histoire de France. Son mandat a été initié dans le but de redonner un souffle au second quinquennat d'Emmanuel Macron et de préparer les élections européennes. Il a mis en avant des thèmes comme le 'réarmement civique' et l'autorité de l'État. Son bilan, bien que court, a été marqué par le début d'une nouvelle dynamique médiatique, mais aussi par des critiques sur l'absence de réformes de fond et sa position face à la majorité parlementaire.",
//            "11 janvier 2024 - 5 septembre 2024",
//            "Manque de réformes de fond, désignation comme 'Premier ministre de la communication'",
//            R.drawable.g_attal
//        ),
//        Article(
//            "François Bayrou : de la Planification à la démission",
//            "Ancien ministre de la Justice du premier gouvernement d'Édouard Philippe, François Bayrou a été rappelé par Emmanuel Macron en décembre 2024 pour prendre la tête du gouvernement. Chargé de la Planification écologique et énergétique, il a eu pour mission principale de stabiliser la situation politique et d'assurer l'adoption du budget 2025. Son mandat, le plus court de l'histoire des Premiers ministres français sous la Vᵉ République, s'est achevé hier, suite au vote d'une motion de censure à l'Assemblée nationale.",
//            "13 décembre 2024 - 9 septembre 2025",
//            "Censure et démission du gouvernement, critique de sa courte durée",
//            R.drawable.f_bayrou
//        )
//    )
//    _articles.value = initialArticles
//}
//
//fun addArticle() {
//    viewModelScope.launch {
//        val newArticle = Article(
//            "Article de Test Dynamique ${_articles.value.size + 1}",
//            "Ceci est un article ajouté dynamiquement pour tester la réactivité de la liste",
//            "Ajouté récemment",
//            "Aucun déboire majeur, juste un test !",
//            R.drawable.article_placeholder
//        )
//        // Mise à jour de la liste en créant une nouvelle instance
//        _articles.value = _articles.value + newArticle
//    }
//}