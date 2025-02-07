package com.example.moviesapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MovieDetails
import com.example.moviesapp.domain.use_cases.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
):  ViewModel() {
    private val _uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> get() = _uiState

    fun getMovieDetails(movieId: String) = viewModelScope.launch {
        _uiState.update { DetailUiState(isLoading = true) }

        val response = getMovieDetailsUseCase.invoke(movieId)

        if (response.isSuccess) {
            _uiState.update { it.copy(movieDetails = response.getOrThrow()) }
        } else {
            _uiState.update { it.copy(error = response.exceptionOrNull().toString() ) }
        }
    }
}

data class DetailUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val movies: List<Movie>? = null,
    val movieDetails: MovieDetails? = null
)