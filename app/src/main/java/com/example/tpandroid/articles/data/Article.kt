package com.example.tpandroid.articles.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// @JsonClass(generateAdapter = true) : Cette annotation indique à la bibliothèque Moshi
// de générer automatiquement un adaptateur pour cette classe de données. Cet adaptateur est
// essentiel pour que Moshi puisse convertir les données JSON reçues de l'API
// en un objet Kotlin `Article` et vice-versa.
@JsonClass(generateAdapter = true)
// data class Article(...) : Définit une classe de données en Kotlin, parfaite pour
// contenir des données d'une manière concise et structurée. Les classes de données
// incluent automatiquement des méthodes utiles comme `equals()`, `hashCode()`, `toString()`, etc.,
// ce qui est idéal pour les modèles de données.
data class Article(
    // @Json(name = "id") : Cette annotation mappe le nom de la variable Kotlin `id`
    // au nom de la clé JSON "id". L'ID est souvent l'identifiant unique d'un article.
    @Json(name = "id") val id: String?,
    // @Json(name = "title") : Mappe le nom de la variable `title` à la clé JSON "title".
    @Json(name = "title") val title: String?,
    // @Json(name = "desc") : Mappe `description` à la clé JSON "desc".
    @Json(name = "desc") val description: String?,
    // @Json(name = "author") : Mappe `author` à "author".
    @Json(name = "author") val author: String?,
    // @Json(name = "imgPath") : Mappe `imageUrl` à "imgPath".
    @Json(name = "imgPath") val imageUrl: String?,
)