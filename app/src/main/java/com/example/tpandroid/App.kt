package com.example.tpandroid

import android.app.Application
import com.example.tpandroid.common.AppContextHelper

// La classe `App` hérite de `android.app.Application`. Cela signifie qu'elle est la
// classe principale de l'application et que son cycle de vie est lié à celui de
// l'application elle-même. C'est l'endroit idéal pour effectuer des initialisations
// qui doivent être faites une seule fois au démarrage.
class App : Application() {
    // La méthode `onCreate()` est appelée une seule fois lorsque l'application
    // démarre. C'est l'entrée principale de votre application.
    override fun onCreate() {
        // Appelle la méthode `onCreate()` de la classe parente. C'est une bonne pratique
        // d'appeler `super.onCreate()` en premier.
        super.onCreate()
        // Appelle la méthode `initialize()` de l'objet singleton `AppContextHelper`.
        // Cela permet de stocker le contexte de l'application de manière globale et de
        // le rendre accessible depuis n'importe où dans l'application, ce qui est
        // utile pour les opérations qui nécessitent un contexte, comme l'affichage de
        // toasts ou le chargement de ressources.
        AppContextHelper.initialize(this)
        // Le commentaire suggère d'initialiser d'autres helpers ici, comme
        // `AppProgressHelpers` ou `AppAlertHelpers`, si ces helpers ont besoin
        // d'un contexte pour fonctionner (ce qui n'est pas le cas pour les versions
        // de ces helpers vues précédemment).
    }
}