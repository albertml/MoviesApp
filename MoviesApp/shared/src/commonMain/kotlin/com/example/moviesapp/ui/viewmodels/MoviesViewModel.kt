package com.example.moviesapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MovieDetails
import com.example.moviesapp.domain.use_cases.GetMoviesUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class MoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    //    val uiState = _uiState.asStateFlow().common()
    val uiState: StateFlow<UiState> get() = _uiState

    private val _query = MutableStateFlow("")

    init {
        viewModelScope.launch {
            _query.debounce(1000)
                .filter { it.isNotEmpty() }
                .collectLatest {
                    getMovies(it)
                }
        }
    }

    fun updateQuery(s: String) {
        _query.update { s }
    }

    fun getMovies(search: String) = viewModelScope.launch {
        _uiState.update { UiState(isLoading = true) }
        val response = getMoviesUseCase.invoke(search)

        if (response.isSuccess) {
            _uiState.update { it.copy(movies = response.getOrThrow()) }
        } else {
            _uiState.update { it.copy(error = response.exceptionOrNull().toString() ) }
        }
    }
}

data class UiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val movies: List<Movie>? = null,
    val movieDetails: MovieDetails? = null
)