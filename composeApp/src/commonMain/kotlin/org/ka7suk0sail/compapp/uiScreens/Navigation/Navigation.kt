package org.ka7suk0sail.compapp.uiScreens.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import compapp.composeapp.generated.resources.Res
import compapp.composeapp.generated.resources.api_key
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource
import org.ka7suk0sail.compapp.Models.Repository.EducationsRepository
import org.ka7suk0sail.compapp.Models.Service.EducationsService
import org.ka7suk0sail.compapp.Models.Repository.FavorsRepository
import org.ka7suk0sail.compapp.Models.Service.FavorsService
import org.ka7suk0sail.compapp.Models.Repository.RidesRepository
import org.ka7suk0sail.compapp.Models.Service.RidesService
import org.ka7suk0sail.compapp.ViewModels.EducationDetailViewModel
import org.ka7suk0sail.compapp.uiScreens.detail.FavorDetailsScreen
import org.ka7suk0sail.compapp.uiScreens.detail.EducationDetailsScreen
import org.ka7suk0sail.compapp.ViewModels.FavorDetailViewModel
import org.ka7suk0sail.compapp.ViewModels.RideDetailViewModel
import org.ka7suk0sail.compapp.uiScreens.detail.RideDetailsScreen
import org.ka7suk0sail.compapp.uiScreens.home.HomeScreen
import org.ka7suk0sail.compapp.uiScreens.login.LoginScreen
import org.ka7suk0sail.compapp.uiScreens.signup.SignUpScreen
import org.ka7suk0sail.compapp.uiScreens.welcome.WelcomeScreen
import org.ka7suk0sail.compapp.ViewModels.HomeViewModel

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val favorsRepository = rememberFavorsRepository()
    val educationsRepository = rememberEducationsRepository()
    val ridesRepository = rememberRidesRepository()

    NavHost(navController = navController, startDestination = "welcome"){
        composable("welcome") {
            WelcomeScreen(
                toLoginScreen = { toLoginScreen(navController) }
            )
        }

        composable("login") {
            LoginScreen(
                toHomeScreen = { toHomeScreen(navController) },
                toSignUpScreen = { toSignUpScreen(navController) }
            )
        }

        composable("signup") {
            SignUpScreen(
                onBack = { onBack(navController) }
            )
        }

        composable("home"){
            HomeScreen(
                onFavorClick = { favor ->
                    navController.navigate("favorDetails/${favor.favorID}")
                },
                onEducationClick = { education ->
                    navController.navigate("educationDetails/${education.educationID}")
                },
                onRideClick = { ride ->
                    navController.navigate("rideDetails/${ride.rideID}")
                },
                vm = viewModel { HomeViewModel(favorsRepository, educationsRepository, ridesRepository) }
            )
        }

    }
}

@Composable
private fun rememberFavorsRepository(
    apiKey: String = stringResource(Res.string.api_key)
): FavorsRepository = remember{
    val client =
        HttpClient{
            install(ContentNegotiation){
                json(Json{
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest){
                url{
                    protocol = URLProtocol.HTTPS
                    host = "api.themoviedb.org"
                    parameters.append("api_key", apiKey)
                }
            }
        }
    FavorsRepository(FavorsService(client))
}

@Composable
private fun rememberEducationsRepository(
    apiKey: String = stringResource(Res.string.api_key)
): EducationsRepository = remember {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.themoviedb.org"
                parameters.append("api_key", apiKey)
            }
        }
    }
    EducationsRepository(EducationsService(client))
}

@Composable
private fun rememberRidesRepository(
    apiKey: String = stringResource(Res.string.api_key)
): RidesRepository = remember {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.themoviedb.org"
                parameters.append("api_key", apiKey)
            }
        }
    }
    RidesRepository(RidesService(client))
}

fun toLoginScreen(navController: NavController) {
    navController.navigate("login") {
        popUpTo("welcome") { inclusive = true }
    }
}

fun toSignUpScreen(navController: NavController) {
    navController.navigate("signup")
}

fun toHomeScreen(navController: NavController) {
    navController.navigate("home") {
        popUpTo("login") { inclusive = true }
    }
}

fun onBack(navController: NavController) {
    navController.popBackStack()
}