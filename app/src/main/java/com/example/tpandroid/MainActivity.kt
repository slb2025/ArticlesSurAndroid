package com.example.tpandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tpandroid.article.Article
import com.example.tpandroid.ui.screens.ArticleListScreen
import com.example.tpandroid.ui.screens.ForgotPasswordScreen
import com.example.tpandroid.ui.screens.LoginScreen
import com.example.tpandroid.ui.screens.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {

                composable("login") {
                    LoginScreen(navController = navController)
                }

                composable("forgotPassword") {
                    ForgotPasswordScreen(navController = navController)
                }

                composable("register") {
                    RegisterScreen(navController = navController)
                }

                composable("articles") {
                    val articles = listOf(
                        Article(
                            "Édouard Philippe : l'architecte des grandes réformes",
                            "Premier chef de gouvernement d'Emmanuel Macron, Édouard Philippe a piloté des réformes majeures comme la suppression de l'ISF et la réforme de la SNCF. Son mandat a été marqué par la crise des 'Gilets Jaunes' en 2018 et l'entrée dans la pandémie de COVID-19. Très populaire à son départ, il a su se forger une image d'homme d'État face aux crises successives. Ses déboires incluent l'affaire Benalla et la gestion de la crise des 'Gilets Jaunes', vivement critiquée pour la répression policière.",
                            "21 juin 2017 - 3 juillet 2020",
                            "Crise des 'Gilets Jaunes', affaire Benalla",
                            R.drawable.e_philippe
                        ),
                        Article(
                            "Jean Castex : le 'Monsieur Déconfinement'",
                            "Surnommé le 'Monsieur Déconfinement', Jean Castex a été nommé Premier ministre en pleine crise sanitaire du COVID-19. Son mandat a été entièrement dédié à la gestion de la pandémie, avec la mise en œuvre de mesures de restriction et de soutien économique. Il a également porté la loi 'Climat et résilience' et la loi '3DS'. Son style jugé plus proche et son franc-parler ont marqué son passage à Matignon. Il a été critiqué pour sa gestion des pass sanitaires et des confinements successifs.",
                            "3 juillet 2020 - 16 mai 2022",
                            "Critiques sur la gestion de la crise sanitaire, utilisation des 49.3 pour la loi climat",
                            R.drawable.j_castex
                        ),
                        Article(
                            "Élisabeth Borne : une mandature sous le signe du 49.3",
                            "Élisabeth Borne est devenue la deuxième femme Première ministre de l'histoire de France. Son action a été fortement dominée par la réforme des retraites, qui a provoqué d'importantes manifestations dans tout le pays. Confrontée à une majorité relative à l'Assemblée nationale, elle a eu recours à de multiples reprises à l'article 49.3 de la Constitution pour faire passer ses textes. Ses déboires sont directement liés à l'impopularité de la réforme des retraites et à l'usage controversé du 49.3, qui a suscité de vives critiques de l'opposition et des syndicats.",
                            "16 mai 2022 - 8 janvier 2024",
                            "Réforme des retraites, usage massif du 49.3, contestation sociale",
                            R.drawable.e_borne
                        ),
                        Article(
                            "Gabriel Attal : le plus jeune Premier ministre de la Vᵉ République",
                            "Nommé à seulement 34 ans, Gabriel Attal est le plus jeune Premier ministre de l'histoire de France. Son mandat a été initié dans le but de redonner un souffle au second quinquennat d'Emmanuel Macron et de préparer les élections européennes. Il a mis en avant des thèmes comme le 'réarmement civique' et l'autorité de l'État. Son bilan, bien que court, a été marqué par le début d'une nouvelle dynamique médiatique, mais aussi par des critiques sur l'absence de réformes de fond et sa position face à la majorité parlementaire.",
                            "11 janvier 2024 - 5 septembre 2024",
                            "Manque de réformes de fond, désignation comme 'Premier ministre de la communication'",
                            R.drawable.g_attal
                        ),
                        Article(
                            "François Bayrou : de la Planification à la démission",
                            "Ancien ministre de la Justice du premier gouvernement d'Édouard Philippe, François Bayrou a été rappelé par Emmanuel Macron en décembre 2024 pour prendre la tête du gouvernement. Chargé de la Planification écologique et énergétique, il a eu pour mission principale de stabiliser la situation politique et d'assurer l'adoption du budget 2025. Son mandat, le plus court de l'histoire des Premiers ministres français sous la Vᵉ République, s'est achevé hier, suite au vote d'une motion de censure à l'Assemblée nationale.",
                            "13 décembre 2024 - 9 septembre 2025",
                            "Censure et démission du gouvernement, critique de sa courte durée",
                            R.drawable.f_bayrou
                        )
                    )
                    ArticleListScreen(navController = navController, articles = articles)
                }
            }
        }
    }
}