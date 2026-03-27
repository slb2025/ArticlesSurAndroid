// Indique que le fichier est situé dans le package `com.example.tpandroid.api`.
// Cela aide à organiser les classes dans une structure de répertoire logique.
package com.example.tpandroid.api

// Importe la classe `Json` de la bibliothèque Moshi.
// Elle est utilisée pour mapper le nom d'une variable Kotlin à un nom de clé JSON.
import com.squareup.moshi.Json
// Importe la classe `JsonClass` de la bibliothèque Moshi.
// L'annotation `@JsonClass` génère automatiquement un adaptateur pour cette classe,
// ce qui est nécessaire pour la désérialisation (transformer le JSON en objet Kotlin).
import com.squareup.moshi.JsonClass

// `@JsonClass(generateAdapter = true)` : Cette annotation indique à Moshi de générer
// automatiquement le code nécessaire pour convertir le JSON en un objet `ApiResponse`
// et vice-versa. Cela simplifie la gestion des données de l'API.
@JsonClass(generateAdapter = true)
// `data class ApiResponse<T>` : Définit une classe de données générique appelée `ApiResponse`.
// `<T>` est un paramètre de type qui peut être remplacé par n'importe quel type de données.
// Cela rend la classe réutilisable pour différentes API qui renvoient des données de types variés.
data class ApiResponse<T>(
    // `@Json(name = "code")` : Mappe la variable `code` de l'objet Kotlin
    // à la clé "code" dans le JSON. La variable `code` stocke une chaîne de caractères
    // qui représente le statut de la réponse (ex: "SUCCESS", "ERROR").
    @Json(name = "code") val code: String,
    // `@Json(name = "message")` : Mappe la variable `message` à la clé "message" du JSON.
    // Elle contient un message descriptif concernant la réponse.
    @Json(name = "message") val message: String,
    // `@Json(name = "data")` : Mappe la variable `data` à la clé "data" du JSON.
    // La variable `data` est de type générique `T` et peut contenir les données
    // réelles de la réponse. Le `?` indique que la valeur peut être `null`, ce qui est
    // utile pour les réponses d'erreur qui n'ont pas de données.
    @Json(name = "data") val data: T?
)