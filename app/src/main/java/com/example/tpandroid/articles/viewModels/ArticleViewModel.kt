package com.example.tpandroid.articles.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.api.RetrofitTools
import com.example.tpandroid.articles.data.Article
import com.example.tpandroid.common.AppAlertHelpers
import com.example.tpandroid.common.AppProgressHelpers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// La classe ArticleViewModel est une ViewModel qui gère la logique de l'interface utilisateur
// liée aux articles, y compris la récupération, la sauvegarde et la suppression des données.
// Elle hérite de la classe ViewModel pour survivre aux changements de configuration.
class ArticleViewModel : ViewModel() {

    // _articles est un MutableStateFlow privé qui contient la liste des articles.
    // Un MutableStateFlow est une classe qui permet d'émettre des mises à jour de données
    // aux collecteurs (comme l'interface utilisateur) qui y sont abonnés.
    // Il est privé pour que l'accès et les modifications soient limités à cette classe.
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    // articles est un StateFlow public, une version en lecture seule de _articles.
    // Il expose l'état de la liste des articles à l'interface utilisateur de manière sécurisée,
    // garantissant que la vue ne peut pas modifier directement la liste.
    val articles: StateFlow<List<Article>> = _articles.asStateFlow()
    // _selectedArticle et selectedArticle gèrent l'état de l'article sélectionné
    // de la même manière que pour la liste des articles.
    private val _selectedArticle = MutableStateFlow<Article?>(null)
    val selectedArticle: StateFlow<Article?> = _selectedArticle.asStateFlow()

    // _articleToEdit et articleToEdit gèrent l'état de l'article en cours d'édition.
    private val _articleToEdit = MutableStateFlow<Article?>(null)
    val articleToEdit: StateFlow<Article?> = _articleToEdit.asStateFlow()


    // Fonction pour récupérer la liste de tous les articles depuis l'API.
    fun fetchArticles() {
        AppProgressHelpers.show("Chargement des articles...")
        viewModelScope.launch {
            try {
                val apiResponse = RetrofitTools.apiService.getArticles()
                if (apiResponse.code == "200") {
                    _articles.value = apiResponse.data ?: emptyList()
                    AppAlertHelpers.show(apiResponse.message)
                } else {
                    AppAlertHelpers.show(apiResponse.message)
                }
            } catch (e: Exception) {
                Log.e("ArticleViewModel", "Erreur lors du chargement des articles : ${e.message}", e)
                AppAlertHelpers.show("Erreur de connexion : impossible de charger les articles.")
            } finally {
                AppProgressHelpers.close()
            }
        }
    }

    // Fonction de rafraîchissement silencieuse
    private fun silentFetchArticles() {
        viewModelScope.launch {
            try {
                val apiResponse = RetrofitTools.apiService.getArticles()
                if (apiResponse.code == "200") {
                    _articles.value = apiResponse.data ?: emptyList()
                }
            } catch (e: Exception) {
                // Gérer l'erreur silencieusement ou la logger, mais ne pas afficher d'alerte à l'utilisateur
                Log.e("ArticleViewModel", "Erreur silencieuse lors du rafraîchissement des articles : ${e.message}", e)
            }
        }
    }

    // Fonction pour récupérer un article par son identifiant.
    fun fetchArticleById(id: String) {
        viewModelScope.launch {
            AppProgressHelpers.show("Chargement de l'article...")
            try {
                // Appelle l'API pour obtenir l'article par son ID.
                val response = RetrofitTools.apiService.getArticleById(id)
                if (response.code == "200") {
                    // Met à jour la valeur de l'article sélectionné.
                    _selectedArticle.value = response.data
                }
            } catch (e: Exception) {
                // Gère l'erreur de récupération.
                AppAlertHelpers.show("Erreur lors de la récupération de l'article.")
                _selectedArticle.value = null
            } finally {
                AppProgressHelpers.close()
            }
        }
    }

    // Fonction de sauvegarde de l'article
    fun saveArticle(article: Article) {
        viewModelScope.launch {
            AppProgressHelpers.show("Sauvegarde de l'article...")
            try {
                val response = RetrofitTools.apiService.saveArticle(article)
                if (response.code == "200") {
                    // Afficher le message spécifique de la sauvegarde
                    AppAlertHelpers.show(response.message)
                    // Puis, rafraîchir la liste SANS afficher de message
                    silentFetchArticles()
                    _articleToEdit.value = null
                } else {
                    AppAlertHelpers.show(response.message)
                }
            } catch (e: Exception) {
                AppAlertHelpers.show("Erreur lors de la sauvegarde.")
            } finally {
                AppProgressHelpers.close()
            }
        }
    }

    // Fonction de suppression de l'article
    fun deleteArticle(articleId: String) {
        viewModelScope.launch {
            try {
                AppProgressHelpers.show("Suppression de l'article...")
                val response = RetrofitTools.apiService.deleteArticle(articleId)

                if (response.isSuccessful) {
                    // Afficher le message spécifique de la suppression
                    AppAlertHelpers.show("Article supprimé avec succès.")
                    // Puis, rafraîchir la liste SANS afficher de message
                    silentFetchArticles()
                } else {
                    AppAlertHelpers.show("Échec de la suppression.")
                }
            } catch (e: Exception) {
                AppAlertHelpers.show("Erreur de connexion : ${e.message}")
            } finally {
                AppProgressHelpers.close()
            }
        }
    }

    fun setArticleToEdit(article: Article?) {
        _articleToEdit.value = article
    }

    fun onLogout() {
        AppAlertHelpers.show("Vous avez été déconnecté.")
    }
}