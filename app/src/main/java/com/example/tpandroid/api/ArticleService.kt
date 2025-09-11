package com.example.tpandroid.api

import com.example.tpandroid.articles.data.Article
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ArticleService {
    @GET("articles")
    suspend fun getArticles(): ApiResponse<List<Article>>

    @GET("articles/{id}")
    suspend fun getArticleById(@Path("id") articleId: String): ApiResponse<Article>

    @POST("articles/save")
    suspend fun saveArticle(@Body article: Article): ApiResponse<Article>

    @DELETE("articles/{id}")
    suspend fun deleteArticle(@Path("id") articleId: String): ApiResponse<Void>

}