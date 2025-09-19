package com.example.tpandroid.common

import android.content.Context

// L'objet AppContextHelper est un singleton, ce qui signifie qu'une seule instance
// de cette classe existera pendant toute la durée de vie de l'application.
// Il est utilisé pour fournir un accès global et centralisé au contexte de l'application.
object AppContextHelper {

    // `appContext` est une variable privée qui stocke le contexte de l'application.
    // Elle est de type `Context?` (nullable) et est initialisée à `null`.
    private var appContext: Context? = null

    // La fonction `initialize` est conçue pour être appelée une seule fois
    // au démarrage de l'application (par exemple, dans la classe Application)
    // afin de définir le contexte de l'application.
    // Le test `if (appContext == null)` garantit que le contexte ne sera pas réinitialisé.
    fun initialize(context: Context) {
        if (appContext == null) {
            appContext = context.applicationContext
        }
    }

    // La fonction `getContext` permet d'obtenir le contexte de l'application
    // depuis n'importe quelle partie de l'application.
    // L'opérateur Elvis (`?:`) vérifie si `appContext` est `null`.
    // Si c'est le cas, il lève une `IllegalStateException` pour indiquer une erreur
    // de programmation : le contexte doit être initialisé avant d'être utilisé.
    // Sinon, il retourne la valeur de `appContext`.
    fun getContext(): Context {
        return appContext ?: throw IllegalStateException("Le contexte de l'application n'a pas été initialisé.")
    }
}