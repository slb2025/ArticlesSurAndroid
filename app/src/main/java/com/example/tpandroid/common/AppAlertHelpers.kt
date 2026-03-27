package com.example.tpandroid.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// Cette classe de données modélise l'état d'une boîte de dialogue d'alerte.
// Elle contient deux propriétés : `isShow` pour savoir si la boîte doit être affichée,
// et `message` pour le texte à afficher. Les valeurs par défaut sont définies pour
// garantir un état initial cohérent.
data class AlertDialogModelData(
    val isShow : Boolean = false,
    val message : String = ""
)

// L'objet `AppAlertHelpers` est un singleton. Cela signifie qu'il n'existe qu'une seule
// instance de cette classe dans toute l'application, ce qui est idéal pour gérer un état global
// comme l'affichage d'une alerte.
object AppAlertHelpers {
    // `_alertModelData` est un `MutableStateFlow` privé qui contient l'état de l'alerte.
    // Il est privé pour que l'état ne puisse être modifié qu'à travers les méthodes
    // définies dans cet objet.
    private val _alertModelData = MutableStateFlow(AlertDialogModelData())
    // `alertModelData` est un `StateFlow` public et en lecture seule.
    // L'interface utilisateur (UI) peut s'y abonner pour recevoir des notifications
    // chaque fois que l'état de l'alerte change. Elle ne peut pas modifier l'état directement.
    val alertModelData = _alertModelData.asStateFlow()

    // La méthode `show` met à jour l'état pour afficher l'alerte avec un message donné.
    // `_alertModelData.value.copy(...)` crée une nouvelle instance de `AlertDialogModelData`
    // en copiant l'état actuel et en ne modifiant que les propriétés spécifiées (`isShow` et `message`).
    // Cela déclenche une mise à jour pour tous les collecteurs (l'UI).
    fun show(message: String) {
        _alertModelData.value = _alertModelData.value.copy(isShow = true, message = message)
    }

    // La méthode `close` met à jour l'état pour masquer l'alerte.
    // Elle ne change que la propriété `isShow` à `false`, signalant à l'UI de fermer la boîte de dialogue.
    fun close() {
        _alertModelData.value = _alertModelData.value.copy(isShow = false)
    }
}