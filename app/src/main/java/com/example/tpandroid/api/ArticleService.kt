package com.example.tpandroid.api

import com.example.tpandroid.articles.data.Article
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// L'interface ArticleService définit les requêtes API pour la gestion des articles.
// Elle est utilisée par Retrofit pour créer un client HTTP qui communique avec le serveur.
interface ArticleService {
    // @GET("/articles") : Indique une requête GET sur le point de terminaison "/articles".
    // La fonction `getArticles` est une fonction de suspension (suspend fun),
    // ce qui signifie qu'elle est asynchrone et doit être appelée depuis une coroutine.
    // Le type de retour attendu est `ApiResponse<List<Article>>`,
    // une liste d'objets `Article` encapsulée dans une réponse générique d'API.
    @GET("/articles")
    suspend fun getArticles(): ApiResponse<List<Article>>

    // @GET("articles/{id}") : Indique une requête GET pour un article spécifique.
    // L'identifiant de l'article est passé dynamiquement dans l'URL.
    // @Path("id") articleId: String : L'annotation `@Path` lie la valeur de la variable
    // `articleId` au placeholder `{id}` dans l'URL.
    // Le type de retour est `ApiResponse<Article>`, un objet `Article` unique.
    @GET("articles/{id}")
    suspend fun getArticleById(@Path("id") articleId: String): ApiResponse<Article>

    // @POST("articles/save") : Indique une requête POST pour sauvegarder ou mettre à jour un article.
    // @Body article: Article : L'annotation `@Body` envoie l'objet `article` sérialisé
    // en tant que corps de la requête.
    // Le type de retour est `ApiResponse<Article>`, car le serveur peut retourner l'article
    // sauvegardé (avec des informations potentiellement mises à jour comme un identifiant).
    @POST("articles/save")
    suspend fun saveArticle(@Body article: Article): ApiResponse<Article>

    // @DELETE("articles/{id}") : Indique une requête DELETE pour supprimer un article.
    // Le paramètre de l'URL est l'identifiant de l'article à supprimer.
    // Le type de retour est `ApiResponse<Void>`, où `Void` indique qu'aucune donnée
    // n'est attendue dans le corps de la réponse de l'API.
    @DELETE("articles/{id}")
    suspend fun deleteArticle(@Path("id") articleId: String): Response<Unit>
}