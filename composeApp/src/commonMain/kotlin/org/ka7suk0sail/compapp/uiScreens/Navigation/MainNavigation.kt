package org.ka7suk0sail.compapp.uiScreens.Navigation
/*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.ka7suk0sail.compapp.Models.Repository.EducationsRepository
import org.ka7suk0sail.compapp.Models.Repository.FavorsRepository
import org.ka7suk0sail.compapp.Models.Repository.RidesRepository
import org.ka7suk0sail.compapp.ViewModels.HomeViewModel
import org.ka7suk0sail.compapp.uiScreens.home.HomeScreen

@Composable
fun MainNavigation(
    favorsRepository: FavorsRepository,
    educationsRepository: EducationsRepository,
    ridesRepository: RidesRepository
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
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
                vm = viewModel {
                    HomeViewModel(favorsRepository, educationsRepository, ridesRepository)
                }
            )
        }

        // Detalle de rutas se manejan en otro archivo
        addDetailNavigation(navController, favorsRepository, educationsRepository, ridesRepository)
    }
}
*/