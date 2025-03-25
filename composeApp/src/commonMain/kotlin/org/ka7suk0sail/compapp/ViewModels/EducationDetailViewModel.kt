package org.ka7suk0sail.compapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.ka7suk0sail.compapp.Models.Data.Education
import org.ka7suk0sail.compapp.Models.Repository.EducationsRepository
import androidx.compose.runtime.*

class EducationDetailViewModel(
    private val educationID: Int,
    private val repository: EducationsRepository
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        loadEducationDetails()
    }

    fun loadEducationDetails() {
        viewModelScope.launch {
            state = UiState(loading = true)
            val education = repository.fetchEducationById(educationID)
            state = UiState(loading = false, education = education)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val education: Education? = null
    )
}
