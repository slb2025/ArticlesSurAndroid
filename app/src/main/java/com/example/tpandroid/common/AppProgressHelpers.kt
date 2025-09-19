package com.example.tpandroid.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// L'objet AppProgressHelpers est un singleton, ce qui signifie qu'il n'y a qu'une seule instance
// de cette classe dans toute l'application. Il est utilisé pour gérer l'état d'une barre de
// progression ou d'un indicateur de chargement global, accessible depuis n'importe où.
object AppProgressHelpers {
    // `_isLoading` est un `MutableStateFlow` privé qui contient l'état de chargement
    // (vrai si le chargement est en cours, faux sinon). Un `MutableStateFlow` est
    // modifiable et permet aux composants de l'interface utilisateur d'observer
    // les changements de son état.
    private val _isLoading = MutableStateFlow(false)
    // `isLoading` est un `StateFlow` public, une version en lecture seule de `_isLoading`.
    // Il expose l'état de chargement à la vue de manière sécurisée, l'empêchant de
    // le modifier directement.
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // `_message` est un `MutableStateFlow` privé qui contient le message textuel
    // à afficher à côté de l'indicateur de chargement.
    private val _message = MutableStateFlow("")
    // `message` est le `StateFlow` public et en lecture seule correspondant.
    val message: StateFlow<String> = _message.asStateFlow()

    // La fonction `show` est appelée pour afficher l'indicateur de chargement.
    // Elle prend un paramètre `text` (avec une valeur par défaut) pour le message.
    // Elle met d'abord à jour le message, puis définit `_isLoading` sur `true`,
    // ce qui déclenche une mise à jour de l'UI pour afficher le chargement.
    fun show(text: String = "Chargement...") {
        _message.value = text
        _isLoading.value = true
    }

    // La fonction `close` est appelée pour masquer l'indicateur de chargement.
    // Elle met simplement `_isLoading` sur `false`, ce qui signale à l'UI de
    // masquer la barre de progression.
    fun close() {
        _isLoading.value = false
    }
}