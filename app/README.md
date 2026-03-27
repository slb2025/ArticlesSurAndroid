📖 Projet d'Application Android "TP Android"

- Ce projet est une application Android développée en Kotlin, utilisant Jetpack Compose pour l'interface utilisateur
- Son objectif principal est de démontrer la consommation d'une API web pour gérer une liste d'articles et l'authentification des utilisateurs

✨ Fonctionnalités

L'application permet aux utilisateurs de :
- S'authentifier (inscription, connexion et réinitialisation de mot de passe) via une API REST.
- Consulter la liste des articles.
- Ajouter, modifier et supprimer un article.
- Consulter les détails d'un article spécifique.
- Naviguer facilement entre les écrans grâce à la navigation Jetpack.

💻 Technologies et Architecture

Ce projet utilise une architecture moderne et les dernières technologies Android pour garantir un code propre et maintenable.
- Langage : Kotlin
- Interface utilisateur : Jetpack Compose
- Gestion de l'état : Architecture MVVM (Model-View-ViewModel)
- Navigation : Jetpack Navigation Compose
- Réseau :
     Retrofit : Pour les requêtes HTTP.
     Moshi : Pour la sérialisation/désérialisation des données JSON.
     Coroutines : Pour les opérations asynchrones.
     Configuration de sécurité réseau : Le fichier network_security_config.xml est utilisé pour permettre le trafic en clair (http) pour les tests en local.

🚀 Démarrage rapide

 Pour faire fonctionner l'application, suivez les étapes ci-dessous :

- Cloner le dépôt : git clone https://github.com/slb2025/ArticlesSurAndroid.git
- Ouvrir dans Android Studio
- Lancer l'API locale : l'application se connecte à une API locale sur l'émulateur (http://10.0.2.2:3000) 
- Assurez-vous d'avoir une API en cours d'exécution sur votre machine locale sur le port 3000
- Les endpoints attendus sont : /articles, /articles/{id}, /login, /signup, etc.
- Lancer l'application sur un émulateur Android ou un appareil physique connecté. Assurez-vous que l'émulateur est sur le même réseau que votre machine locale pour que l'URL 10.0.2.2 fonctionne correctement
- Utilisation de l'API https://github.com/Chocolaterie/ApiArticle
- Login pour tester l'appli avec l'API : toto@gmail.com -- Password : 12345

✍️ Auteur

Stève Le Berre


