package org.ka7suk0sail.compapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.ka7suk0sail.compapp.Models.Data.Ride
import org.ka7suk0sail.compapp.Models.Repository.RidesRepository
import androidx.compose.runtime.*

class RideDetailViewModel(
    private val rideID: Int,
    private val repository: RidesRepository
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        loadRideDetails()
    }

    fun loadRideDetails() {
        viewModelScope.launch {
            state = UiState(loading = true)
            val ride = repository.fetchRideById(rideID)
            state = UiState(loading = false, ride = ride)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val ride: Ride? = null
    )
}
