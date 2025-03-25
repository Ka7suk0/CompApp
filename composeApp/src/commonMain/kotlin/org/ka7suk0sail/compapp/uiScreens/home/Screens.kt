package org.ka7suk0sail.compapp.uiScreens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.ka7suk0sail.compapp.Models.Data.Favor
import org.ka7suk0sail.compapp.Models.Data.Education
import org.ka7suk0sail.compapp.Models.Data.Ride
import org.ka7suk0sail.compapp.ViewModels.HomeViewModel

@Composable
fun FavorsScreen(vm: HomeViewModel, onFavorClick: (Favor) -> Unit) {
    val favors = vm.favorsState
    val loading = vm.loading

    ItemsScreen(
        items = favors,
        onItemClick = { favor -> onFavorClick(favor) },
        loading = loading
    )
}

@Composable
fun EducationScreen(vm: HomeViewModel, onEducationClick: (Education) -> Unit) {
    val educations = vm.educationsState
    val loading = vm.loading

    ItemsScreen(
        items = educations,
        onItemClick = { education -> onEducationClick(education) },
        loading = loading
    )
}

@Composable
fun RidesScreen(vm: HomeViewModel, onRideClick: (Ride) -> Unit) {
    val rides = vm.ridesState
    val loading = vm.loading

    ItemsScreen(
        items = rides,
        onItemClick = { ride -> onRideClick(ride) },
        loading = loading
    )
}

@Composable
fun <T> ItemsScreen(
    items: List<T>,
    onItemClick: (T) -> Unit,
    loading: Boolean,
    modifier: Modifier = Modifier
) {
    if (loading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize().wrapContentSize())
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier
        ) {
            items(items) { item ->
                when (item) {
                    is Favor -> FavorItem(favor = item, onClick = { onItemClick(item) })
                    is Education -> EducationItem(education = item, onClick = { onItemClick(item) })
                    is Ride -> RideItem(ride = item, onClick = { onItemClick(item) })
                    else -> {}
                }
            }
        }
    }
}
