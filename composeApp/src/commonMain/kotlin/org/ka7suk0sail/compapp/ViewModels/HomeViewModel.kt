package org.ka7suk0sail.compapp.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.ka7suk0sail.compapp.Models.Data.Education
import org.ka7suk0sail.compapp.Models.Repository.EducationsRepository
import org.ka7suk0sail.compapp.Models.Data.Favor
import org.ka7suk0sail.compapp.Models.Repository.FavorsRepository
import org.ka7suk0sail.compapp.Models.Data.Ride
import org.ka7suk0sail.compapp.Models.Repository.RidesRepository

class HomeViewModel (
    val favorsRepository: FavorsRepository,
    val educationsRepository: EducationsRepository,
    val ridesRepository: RidesRepository
): ViewModel() {

    var favorsState by mutableStateOf(emptyList<Favor>())
        private set

    var educationsState by mutableStateOf(emptyList<Education>())
        private set

    var ridesState by mutableStateOf(emptyList<Ride>())
        private set

    var loading by mutableStateOf(false)
        private set

    init{
        fetchFavors()
        fetchEducations()
        fetchRides()
    }

    private fun fetchFavors() {
        viewModelScope.launch {
            loading = true
            favorsState = favorsRepository.fetchPopularFavors()
            loading = false
        }
    }

    private fun fetchEducations() {
        viewModelScope.launch {
            loading = true
            educationsState = educationsRepository.fetchPopularEducations()
            loading = false
        }
    }

    private fun fetchRides() {
        viewModelScope.launch {
            loading = true
            ridesState = ridesRepository.fetchPopularRides()
            loading = false
        }
    }
        /*
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(loading = false,
                favors = repository.fetchPopularFavors())
        }
    }
    data class  UiState(
        val loading: Boolean = false,
        val favors: List<Favor> = emptyList()
    )
         */
}
