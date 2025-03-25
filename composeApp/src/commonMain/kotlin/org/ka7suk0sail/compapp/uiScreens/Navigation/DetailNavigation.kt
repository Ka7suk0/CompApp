package org.ka7suk0sail.compapp.uiScreens.Navigation
/*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.ka7suk0sail.compapp.Models.Repository.EducationsRepository
import org.ka7suk0sail.compapp.Models.Repository.FavorsRepository
import org.ka7suk0sail.compapp.Models.Repository.RidesRepository
import org.ka7suk0sail.compapp.ViewModels.EducationDetailViewModel
import org.ka7suk0sail.compapp.ViewModels.FavorDetailViewModel
import org.ka7suk0sail.compapp.ViewModels.RideDetailViewModel
import org.ka7suk0sail.compapp.uiScreens.detail.EducationDetailsScreen
import org.ka7suk0sail.compapp.uiScreens.detail.FavorDetailsScreen
import org.ka7suk0sail.compapp.uiScreens.detail.RideDetailsScreen

fun NavHostController.addDetailNavigation(
    navController: NavHostController,
    favorsRepository: FavorsRepository,
    educationsRepository: EducationsRepository,
    ridesRepository: RidesRepository
) {
    composable(
        route = "favorDetails/{favorID}",
        arguments = listOf(navArgument("favorID") { type = NavType.IntType })
    ) { backStackEntry ->
        val favorID = checkNotNull(backStackEntry.arguments?.getInt("favorID"))
        FavorDetailsScreen(
            vm = viewModel { FavorDetailViewModel(favorID, favorsRepository) },
            onBack = { navController.popBackStack() }
        )
    }

    composable(
        route = "educationDetails/{educationID}",
        arguments = listOf(navArgument("educationID") { type = NavType.IntType })
    ) { backStackEntry ->
        val educationID = checkNotNull(backStackEntry.arguments?.getInt("educationID"))
        EducationDetailsScreen(
            vm = viewModel { EducationDetailViewModel(educationID, educationsRepository) },
            onBack = { navController.popBackStack() }
        )
    }

    composable(
        route = "rideDetails/{rideID}",
        arguments = listOf(navArgument("rideID") { type = NavType.IntType })
    ) { backStackEntry ->
        val rideID = checkNotNull(backStackEntry.arguments?.getInt("rideID"))
        RideDetailsScreen(
            vm = viewModel { RideDetailViewModel(rideID, ridesRepository) },
            onBack = { navController.popBackStack() }
        )
    }
}
*/