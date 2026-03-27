package com.example.tpandroid.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// La classe 'object' RetrofitTools est un singleton, ce qui signifie qu'il n'y aura qu'une seule
// instance de cette classe dans toute l'application. C'est parfait pour gérer des
// ressources partagées comme une instance de Retrofit.
object RetrofitTools {
    // URL de base de l'API. '10.0.2.2' est une adresse IP spéciale pour l'émulateur Android,
    // qui fait référence à la machine locale (votre ordinateur). L'URL commentée est un
    // exemple d'adresse IP locale.
    private const val BASE_URL = "http://10.0.2.2:3000"

    // Instance de Moshi, un convertisseur JSON/Kotlin.
    // Moshi.Builder() crée une nouvelle instance de Moshi.
    // .add(KotlinJsonAdapterFactory()) ajoute un adaptateur qui permet de
    // sérialiser/désérialiser les classes de données Kotlin.
    // .build() construit l'instance finale.
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Méthode pour obtenir l'instance de Retrofit.
    // Retrofit.Builder() crée une nouvelle instance de Retrofit.
    // .baseUrl(BASE_URL) définit l'URL de base pour toutes les requêtes.
    // .addConverterFactory(MoshiConverterFactory.create(moshi)) spécifie que
    // Moshi sera utilisé pour convertir les réponses JSON de l'API en objets Kotlin.
    // .build() construit et retourne l'instance de Retrofit.
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    // `val apiService: ArticleService by lazy { ... }` est une propriété déléguée.
    // `by lazy` signifie que l'initialisation de `apiService` ne se fera que lors du
    // premier accès à cette propriété. C'est une manière efficace d'initialiser une
    // propriété coûteuse en ressources seulement quand elle est nécessaire.
    // `getRetrofit().create(ArticleService::class.java)` crée une implémentation
    // de l'interface `ArticleService` à partir de l'instance de Retrofit. Cette
    // interface définit les points de terminaison de l'API.
    val apiService: ArticleService by lazy {
        getRetrofit().create(ArticleService::class.java)
    }

    // `authApi: AuthService by lazy { ... }` est une autre propriété déléguée qui
    // fait la même chose pour l'interface de service d'authentification.
    // Cela permet de centraliser la création des services API.
    val authApi: AuthService by lazy {
        getRetrofit().create(AuthService::class.java)
    }
}