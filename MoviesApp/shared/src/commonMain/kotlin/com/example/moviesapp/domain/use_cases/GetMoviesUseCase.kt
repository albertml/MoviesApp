package com.example.moviesapp.domain.use_cases

import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.repository.MoviesRepository

class GetMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(search: String): Result<List<Movie>> {
        return try {
            val response = moviesRepository.getMovies(search)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}