package org.ka7suk0sail.compapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.ka7suk0sail.compapp.Models.Data.Favor
import org.ka7suk0sail.compapp.Models.Repository.FavorsRepository
import androidx.compose.runtime.*

class FavorDetailViewModel(
    private val favorID: Int,
    private val repository: FavorsRepository
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        loadFavorDetails()
    }

    fun loadFavorDetails() {
        viewModelScope.launch {
            state = UiState(loading = true)
            val favor = repository.fetchFavorById(favorID)
            state = UiState(loading = false, favor = favor)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val favor: Favor? = null
    )
}
